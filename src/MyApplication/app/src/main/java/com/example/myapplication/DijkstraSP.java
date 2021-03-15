package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class is used to find shortest path using Dijkstra's algorithm
 * 
 * Most of the code is taken from the following textbook and its supporting
 * resources: Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne Some
 * changes have been made to make it so this class can work for our purpose to
 * find the cheapest route based on meal prices in cities.
 */
public class DijkstraSP {
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	private ArrayList<State> arr;

	/**
	 * Dijkstra Constructor using Priority Queues
	 * @param G Graph
	 * @param arr ArrayList of State objects
	 * @param month Month
	 * @param s Source Vertex
	 */
	public DijkstraSP(Graph G, ArrayList<State> arr, int month, int s) {
		edgeTo = new Edge[arr.size()];
		this.arr = arr;
		distTo = new double[arr.size()];
		pq = new IndexMinPQ<Double>(arr.size());
		for (int v = 0; v < arr.size(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		pq.insert(s, 0.0);
		while (!pq.isEmpty())
			relax(G, month, pq.delMin());
	}

	/**
	 * Relax function that takes into account the edges and relaxes them
	 * @param G Graph
	 * @param month Month
	 * @param v Vertex
	 */
	private void relax(Graph G, int month, int v) {
		State source = arr.get(v);
		for (Edge e : G.adj(source)) {
			int w = arr.indexOf(e.otherVert(source));
			double weight = e.otherVert(source).MonthlyHazardNum(month);
			if (distTo[w] > distTo[v] + weight) {
				distTo[w] = distTo[v] + weight;
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.changeKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}

	/**
	 * Distance to vertex
	 * @param v Vertex
	 * @return Distance to vertex
	 */
	public double distTo(int v) {
		return distTo[v];
	}

	/**
	 * Return path to vertex
	 * @param v Vertex
	 * @return Path to vertex
	 */
	public Iterable<Edge> pathTo(int v) {
		Stack<Edge> path = new Stack<Edge>();
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[arr.indexOf(e.aVert())])
			path.push(e);
		return path;
	}
}
