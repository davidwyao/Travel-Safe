import java.util.ArrayList;
import java.util.HashMap;


//change to undirected graph
public class Graph {

	private HashMap<String, ArrayList<State>> graph;

	public Graph() {
		graph = new HashMap<String, ArrayList<State>>();
	}

	public void add(State a, State b) {
		if (graph.get(a.name()) == null) {
			graph.put(a.name(), new ArrayList<State>());
		}
//		if (graph.get(b.name()) == null) {
//			graph.put(b.name(), new ArrayList<State>());
//		}
		graph.get(a.name()).add(b);
		//graph.get(b.name()).add(a);
	}

	public ArrayList<State> adj(State a) {
		return graph.get(a.name());
	}
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(String key : graph.keySet()) {
        	s.append(key + ": ");
        	for(State t : graph.get(key)) {
        		s.append(t + "  ");
        	}
        	s.append("\n");
        }
        return s.toString();
    }

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.add(new State("New"), new State("Void"));
		System.out.println(graph.adj(new State("New")));
	}
}