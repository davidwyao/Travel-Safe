package com.example.myapplication;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Graph Class
 */
public class Graph {

    private HashMap<State, ArrayList<Edge>> graph;

    /**
     * Graph Constructor
     */
    public Graph() {
        graph = new HashMap<State, ArrayList<Edge>>();
    }

    /**
     * Return graph size
     * @return Graph size
     */
    public int V() {
        return graph.size();
    }

    /**
     * Get States
     * @return Set of State Objects
     */
    public Set<State> getKeySet(){
        return graph.keySet();
    }

    /**
     * Add an edge between two states
     * @param a Tail State
     * @param b Head State
     */
    public void add(State a, State b) {
        if (graph.get(a) == null) {
            graph.put(a, new ArrayList<Edge>());
        }

        graph.get(a).add(new Edge(a,b));
    }

    /**
     * Return all the adjacent states of state
     * @param a State object
     * @return ArrayList of adjacent states of State object a
     */
    public ArrayList<Edge> adj(State a) {
        return graph.get(a);
    }

    /**
     * Return string of States and adjacent states
     * @return String of states and adjacent states
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(State key : graph.keySet()) {
            s.append(key + ": ");
            for(Edge t : graph.get(key)) {
                s.append(t + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}