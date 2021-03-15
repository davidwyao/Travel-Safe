package test;

/**
 * An edge of a graph. Vertices represented by Strings.
 * 
 * @author David Yao
 * @version April 10 2020
 */
public class Edge implements Comparable<Edge> {
	private final String v1; // vertex
	private final String v2; // other vertex
	private final double weight;

	/**
	 * Constructor for Edge
	 * 
	 * @param v1
	 *            One vertex.
	 * @param v2
	 *            The other vertex.
	 * @param weight
	 *            The weight of the edge.
	 */
	public Edge(String v1, String v2, double weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	/**
	 * Accessor for one vertex.
	 * 
	 * @return A vertex.
	 */
	public String aVert() {
		return v1;
	}

	/**
	 * Accessor for the vertex that is not the parameter.
	 * 
	 * @param vertex
	 *            The first vertex.
	 * @return The other vertex.
	 */
	public String otherVert(String vertex) {
		if (vertex.equals(this.v1))
			return this.v2;
		else
			return this.v1;
	}

	/**
	 * Accessor for the weight of the edge.
	 * 
	 * @return The weight of the edge.
	 */
	public double weight() {
		return weight;
	}

	public int compareTo(Edge that) {
		if (this.weight() < that.weight())
			return -1;
		else if (this.weight() > that.weight())
			return 1;
		else
			return 0;
	}

	public String toString() {
		return (v1 + " to " + v2 + ", weight " + weight);
	}
}
