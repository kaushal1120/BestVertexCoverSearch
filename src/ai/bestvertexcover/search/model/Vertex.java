package ai.bestvertexcover.search.model;
import java.util.Comparator;

/**
 * Represents a vertex in the input graph of the best vertex cover problem.
 * @author kps9907
 *
 */
public class Vertex {
	//Represents the cost of a vertex
	private int cost;

	//Used to name a vertex with a character.
	private char name;

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public Vertex(char name, int cost) {
		this.name = name;
		this.cost = cost;
	}

	/**
	 * Comparator to compare two vertices by their name to ensure systematic search.
	 */
	private static class VertexNameComparator implements Comparator<Vertex> {
		public int compare(Vertex v1, Vertex v2){
	        if(v1.name > v2.name)
	        	return 1;
	        else if(v1.name < v2.name)
	        	return -1;
	        return 0;
	    }
	}

	/**
	 * Returns a new instance of the above comparator.
	 */
	public static Comparator<Vertex> getVertexNameComparator(){
		return new VertexNameComparator();
	}
}
