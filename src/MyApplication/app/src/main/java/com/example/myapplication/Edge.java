package com.example.myapplication;

/**
 * An edge of a graph. Vertices represented by Strings.
 * @author David Yao
 * @version April 10 2020
 */
public class Edge {
	private final State v1; // vertex
	private final State v2; // other vertex

	/**
	 * Constructor for Edge
	 * @param v1 One vertex.
	 * @param v2 The other vertex.
	 */
	public Edge(State v1, State v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	/**
	 * Accessor for one vertex.
	 * @return A vertex.
	 */
	public State aVert() {
		return v1;
	}

	/**
	 * Accessor for the vertex that is not the parameter.
	 * @param vertex The first vertex.
	 * @return The other vertex.
	 */
	public State otherVert(State vertex) {
		if (vertex.equals(this.v1))
			return this.v2;
		else
			return this.v1;
	}

	/**
	 * Return string "Vertex to Vertex"
	 * @return "Vertex to Vertex"
	 */
	public String toString() {
		return (v1 + " to " + v2);
	}
}
