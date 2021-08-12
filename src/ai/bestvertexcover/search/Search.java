package ai.bestvertexcover.search;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ai.bestvertexcover.search.model.Edge;
import ai.bestvertexcover.search.model.Vertex;

/**
 * Represents a base class for all search algorithms. Holds the input graph for the best vertex
 * cover problem. 
 * @author kps9907
 *
 */
public class Search {
	//V for verbose. C for compact.
	private boolean verboseOutputFlag;

	//Budget specified for the best vertex cover problem.
	private int budget;

	//number of random restarts specified for the hill climbing algorithm
	private int numRandomRestarts;

	protected Map<Character, Vertex> vertexMap = new LinkedHashMap<Character, Vertex>();

	protected List<Vertex> vertexList = new ArrayList<Vertex>();

	protected List<Edge> edgeList = new ArrayList<Edge>();

	/**
	 * Prints the vertices and the edges of the input graph for debugging purposes.
	 */
	public void print() {
		for(char vertex : vertexMap.keySet()) {
			System.out.print(vertex + " ");
		}
		System.out.println();
		for(Edge e : edgeList) {
			System.out.println(e.getVertex1().getName()  + " "+ e.getVertex2().getName());
		}
	}

	/**
	 * Read input for the input graph from the file based on the algorithm.
	 */
	public void getGraphInput(Scanner scanner) {
		String newVertex = "";
		while(scanner.hasNextLine()) {
			newVertex = scanner.next();
			if(!scanner.hasNextInt())
				break;
			vertexMap.put(newVertex.charAt(0), new Vertex(newVertex.charAt(0), scanner.nextInt()));
		}
		vertexList = new ArrayList<Vertex>(vertexMap.values());
		edgeList.add(new Edge(vertexMap.get(newVertex.charAt(0)), vertexMap.get(scanner.next().charAt(0))));
		while(scanner.hasNextLine()) {
			edgeList.add(new Edge(vertexMap.get(scanner.next().charAt(0)), vertexMap.get(scanner.next().charAt(0))));
		}
	}

	public boolean getVerboseOutputFlag() {
		return verboseOutputFlag;
	}

	public void setVerboseOutputFlag(char verboseOutputFlag) {
		if(verboseOutputFlag == 'V')
			this.verboseOutputFlag = true;
		else
			this.verboseOutputFlag = false;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getNumRandomRestarts() {
		return numRandomRestarts;
	}

	public void setNumRandomRestarts(int numRandomRestarts) {
		this.numRandomRestarts = numRandomRestarts;
	}
}
