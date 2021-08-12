package ai.bestvertexcover.search.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a state in the state space that will be created for the state space 
 * search algorithm, namely Iterative Deepening and also for the heuristic algorithm
 * namely HillClimbing.
 * Also represents a neighbour for the HillClimbing algorithm.
 * @author kps9907
 *
 */
public class State {
	//List of vertices in alphabetic order that are part of the current state.
	private List<Vertex> vertexList = new ArrayList<Vertex>();

	//Error calculated for the current state
	private int error;

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public List<Vertex> getVertexList() {
		return vertexList;
	}

	public void setVertexList(List<Vertex> vertexList) {
		this.vertexList = vertexList;
	}

	/**
	 * Prints the vertices that are part of the state along with the total cost
	 * cost of the vertices and the error value of the current state.
	 */
	public void print() {
		int cost = 0;
		if(vertexList.size() == 0)
			System.out.print("{} ");
		for(Vertex v : vertexList) {
			cost += v.getCost();
			System.out.print(v.getName() + " ");
		}
		System.out.println("Cost=" + cost + ". Error=" + error);
	}

	/**
	 * Prints the vertices that are part of the state along with the total cost
	 * cost of the vertices but without the error value of the current state.
	 */
	public void printWithoutError() {
		int cost = 0;
		if(vertexList.size() > 0) {
			for(Vertex v : vertexList) {
				cost += v.getCost();
				System.out.print(v.getName() + " ");
			}
			System.out.println("Cost=" + cost);
		}
	}
}
