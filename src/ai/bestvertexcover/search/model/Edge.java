package ai.bestvertexcover.search.model;

/**
 * Represents an edge in the input graph of the best vertex cover problem.
 * @author kps9907
 *
 */
public class Edge {
	private Vertex vertex1;

	private Vertex vertex2;

	public Vertex getVertex1() {
		return vertex1;
	}
	public void setVertex1(Vertex vertex1) {
		this.vertex1 = vertex1;
	}
	public Vertex getVertex2() {
		return vertex2;
	}
	public void setVertex2(Vertex vertex2) {
		this.vertex2 = vertex2;
	}

	public Edge(Vertex v1, Vertex v2) {
		this.vertex1 = v1;
		this.vertex2 = v2;
	}
}
