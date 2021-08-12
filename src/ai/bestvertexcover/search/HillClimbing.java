package ai.bestvertexcover.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ai.bestvertexcover.search.model.Edge;
import ai.bestvertexcover.search.model.State;
import ai.bestvertexcover.search.model.Vertex;

public class HillClimbing extends Search {
	private Comparator<Vertex> vertexNameComparator = Vertex.getVertexNameComparator();

	/**
	 * Calculates and sets the error for the state passed as parameter.
	 * @param state the state for which error has to be calculated and set.
	 */
	private void setError(State state) {
		int vertexCost = 0;
		int uncoveredEdgeCost = 0;
		for(Vertex v : state.getVertexList()) {
			vertexCost += v.getCost();
		}
		for(Edge e : edgeList) {
			if(!state.getVertexList().contains(e.getVertex1()) && !state.getVertexList().contains(e.getVertex2()))
					uncoveredEdgeCost += Math.min(e.getVertex1().getCost(), e.getVertex2().getCost());
		}
		state.setError(Math.max(0, vertexCost - getBudget()) + uncoveredEdgeCost);
	}

	/**
	 * Creates a start state for the hill climbing algorithm by assigning vertices with probability 0.5
	 * @return State randomly generated state to start the hill climbing algorithm.
	 */
	private State getStartState() {
		Random random = new Random();
		State startState = new State();
		List<Vertex> startStateList = new ArrayList<Vertex>();
		for(Vertex v : vertexList) {
			if(random.nextBoolean())
				startStateList.add(v);
		}
		startState.setVertexList(startStateList);
		setError(startState);
		return startState;
	}

	/**
	 * Creates neighbours for the given state by adding or removing a vertex to and from the given state respectively.
	 * @param state State for which neighbours must be generated.
	 * @return List of neighbours of the given state.
	 */
	private List<State> getNeighbours(State state){
		List<Vertex> vertexList = state.getVertexList();
		List<State> neighbours = new ArrayList<State>();
		//Creates neighbours of the given state by adding one vertex to the vertex list.
		for(Vertex v : vertexMap.values()) {
			if(!vertexList.contains(v)) {
				List<Vertex> newVertexList = new ArrayList<Vertex>(vertexList);
				int i = 0;
				//Finds the position to insert the new vertex to maintain alphabetic order.
				while(i < vertexList.size() && vertexNameComparator.compare(v, vertexList.get(i))==1) {i++;}
				newVertexList.add(i,v);
				State newState = new State();
				newState.setVertexList(newVertexList);
				setError(newState);
				neighbours.add(newState);
				//Stops creating any more neighbours if a neighbour that is a goal state is found.
				if(newState.getError() == 0)
					return neighbours;
			}
		}
		//Creates neighbours of the given state by removing one vertex from the vertex list.
		for(int i = vertexList.size() - 1; i >= 0; i--) {
			List<Vertex> newVertexList = new ArrayList<Vertex>(vertexList);
			newVertexList.remove(i);
			State newState = new State();
			newState.setVertexList(newVertexList);
			setError(newState);
			neighbours.add(newState);
			//Stops creating any more neighbours if a neighbour that is a goal state is found.
			if(newState.getError() == 0)
				return neighbours;
		}	
		return neighbours;
	}

	/**
	 * Runs the hill climbing algorithm.
	 * @param startState
	 * @return
	 */
	public State hillClimbing(State startState) {
		while(true){
			List<State> neighbours = getNeighbours(startState);
			State maximalNeighbour = new State();
			maximalNeighbour.setError(Integer.MAX_VALUE);
			if(getVerboseOutputFlag()) System.out.println("Neighbours");
			//Finds the neighbour with the lowest error.
			for(State s : neighbours) {
				if(getVerboseOutputFlag()) s.print();
				if(s.getError() < maximalNeighbour.getError())
					maximalNeighbour = s;
			}
			//Stop hill climbing and return the current state if the neighbour with 
			//the lowest error still has an error >= that of the current state.
			if(maximalNeighbour.getError() >= startState.getError())
				return startState;
			//Stop hill climbing and return the neighbour with error=0.
			if(maximalNeighbour.getError() == 0)
				return maximalNeighbour;
			startState = maximalNeighbour;
			if(getVerboseOutputFlag()) { System.out.print("\nMove to "); startState.print(); }
		}
	}

	/**
	 * Run random restart hill climbing algorithm.
	 */
	public void randomRestart() {
		int numRandomRestarts = getNumRandomRestarts();
		State resultState = null;
		for(int i = 0; i < numRandomRestarts; i++) {
			//Get random start state.
			State startState = getStartState();
			if(getVerboseOutputFlag()) { System.out.print("Randomly chosen start state "); startState.print(); }
			State result = hillClimbing(startState);
			//If solution is found, stop the random restarts.
			if(result.getError() == 0) {
				resultState = result;
				System.out.print("\nFound solution ");
				resultState.print();
				break;
			}
			else {
				if(resultState == null || result.getError() < resultState.getError())
					resultState = result;
				if(getVerboseOutputFlag()) System.out.println("\nSearch failed\n");
			}
		}
		if(resultState != null && resultState.getError() != 0)
			System.out.println("No solution found.");
	}

	/**
	 * Main function to run Hill Climbing algorithm.
	 * @param args
	 */
	public static void main(String[] args) {
		File inputFile = new File("src/input_files/hillclimbing_input.txt");
		HillClimbing hillClimbing = new HillClimbing();
		try {
			Scanner scanner = new Scanner(inputFile);
			hillClimbing.setBudget(scanner.nextInt());
			hillClimbing.setVerboseOutputFlag(scanner.next().charAt(0));
			hillClimbing.setNumRandomRestarts(scanner.nextInt());
			hillClimbing.getGraphInput(scanner);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		hillClimbing.randomRestart();		
	}
}
