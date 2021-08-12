package ai.bestvertexcover.search;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import ai.bestvertexcover.search.model.Edge;
import ai.bestvertexcover.search.model.State;
import ai.bestvertexcover.search.model.Vertex;

/**
 * Class representing the IterativeDeepening algorithm. Run the main function from this class
 * to run the Iterative Deepening algorithm.
 * @author kps9907
 *
 */
public class IterativeDeepening extends Search {
	//Represents a comparator to compare two vertices of the input graph alphabetically.
	private Comparator<Vertex> vertexNameComparator = Vertex.getVertexNameComparator();

	/**
	 * DFS algorithm used by the Iterative Deepening algorithm.
	 * @param currentState current state being explored in the state space.
	 * @param depth the depth until which iterative deepening is supposed to run
	 * @param currentDepth depth of the current state
	 * @param cost total cost calculated for the current state.
	 * @param edgesCovered edges covered in the depth first search by the current state.
	 * @return result state having error 0 if found else null.
	 */
	public State dfs(State currentState, int depth, int currentDepth, int cost, List<Edge> edgesCovered) {
		if(getVerboseOutputFlag()) currentState.printWithoutError();
		//Found a solution.
		if(edgesCovered.size() == edgeList.size() && cost <= getBudget())
			return currentState;
		//Continue with DFS only if within the depth limit.
		if(currentDepth < depth) {
			//Flag to detect if a new edge was added or not.
			boolean edgeAddedFlag = false;
			for(Vertex v : vertexList) {
				edgeAddedFlag = false;
				//Add a new vertex to the new state only if it isn't already present in the current state and if it alphabetically occurs after the last vertex of the current state.
				//and if the cost of the new state is going to be <= the given budget.
				if(!currentState.getVertexList().contains(v) && cost + v.getCost() <= getBudget() &&
						(currentState.getVertexList().size() == 0 || (currentState.getVertexList().size() > 0 && vertexNameComparator.compare(v, currentState.getVertexList().get(currentState.getVertexList().size()-1)) == 1))) {
					//Creating a new state
					State newState = new State();
					List<Vertex> newVertexList = new ArrayList<Vertex>(currentState.getVertexList());
					newVertexList.add(v);
					newState.setVertexList(newVertexList);
					//Error isn't used in the iterative deepening algorithm and has been set just as a placeholder.
					newState.setError(0);
					for(Edge e : edgeList) {
						if(!edgesCovered.contains(e) && (v.equals(e.getVertex1()) || v.equals(e.getVertex2()))) {
							edgesCovered.add(e);
							edgeAddedFlag = true;
						}
					}
					State state = null;
					if(edgeAddedFlag)
						state = dfs(newState, depth, currentDepth + 1, cost + v.getCost(), edgesCovered);
					if(state != null)
						return state;
					//Remove all those edges that were added just by the addition of this new vertex.
					for(Edge e : edgeList) {
						if((v.equals(e.getVertex1()) && !currentState.getVertexList().contains(e.getVertex2())) || (v.equals(e.getVertex2()) && !currentState.getVertexList().contains(e.getVertex1())))
							edgesCovered.remove(e);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Runs the iterative deepening algorithm.
	 */
	private void run() {
		//Max depth of a state space for the best vertex cover problem.
		int depth = (int) Math.pow(2, vertexMap.size());
		State result = null;
		//Creating an empty state to begin the algorithm with.
		State root = new State();
		List<Vertex> vertexList = new ArrayList<Vertex>();
		root.setVertexList(vertexList);
		root.setError(0);

		for(int i = 1; i <= depth; i++) {
			if(getVerboseOutputFlag()) System.out.println("Depth=" + i);
			//Call DFS to run until depth i.
			result = dfs(root, i, 0, 0, new ArrayList<Edge>());			
			if(getVerboseOutputFlag()) System.out.println();
			//If solution is found, break out of the loop.
			if(result != null) {
				System.out.print("Found solution ");
				result.printWithoutError();
				return;
			}
		}
		System.out.print("No solution found.");
	}

	/**
	 * Main function to run Iterative Deepening algorithm.
	 * @param args
	 */
	public static void main(String[] args) {
		File inputFile = new File("src/input_files/iterativedeepening_input.txt");
		IterativeDeepening iterativeDeepening = new IterativeDeepening();
		try {
			Scanner scanner = new Scanner(inputFile);
			iterativeDeepening.setBudget(scanner.nextInt());
			iterativeDeepening.setVerboseOutputFlag(scanner.next().charAt(0));
			iterativeDeepening.getGraphInput(scanner);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		iterativeDeepening.run();
	}
}
