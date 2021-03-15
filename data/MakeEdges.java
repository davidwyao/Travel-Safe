package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Utility class to make an ArrayList of Edges given a csv.
 *
 * Example dataset from https://data.world/bryon/state-adjacency
 * 
 * @author David Yao
 * @version April 10 2020
 */
public class MakeEdges {
	private static Scanner input;
	private static ArrayList<Edge> edges = new ArrayList<Edge>();
	
	/**
	 * Make an ArrayList of Edges represented in the file.
	 * 
	 * Edge represented as: v1,v2
	 * 
	 * @param file Input file. First line is ignored.
	 * @return ArrayList of Edges represented in the file.
	 * @throws FileNotFoundException If invalid file is given.
	 */
	public ArrayList<Edge> makeEdges(String file) throws FileNotFoundException {
		input = new Scanner(new File(file));
		input.nextLine();
		while (input.hasNextLine()) {
			String[] strings = input.nextLine().split(","); // each line has two states separated by commas
			String v1 = strings[0];
			String v2 = strings[1];
			Edge edge = new Edge(v1, v2, 1); // TODO weight function

			edges.add(edge);
		}
		input.close();
		return edges;
	}
}
