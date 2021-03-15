package com.example.myapplication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Input Class
 * Reads and organizes all input
 */
public class Input {

    /**
     * Reads main data set of all the weather events and organize data based on states
     * @param in modifieddata1.csv
     * @return ArrayList of State Objects
     */
    public static ArrayList<State> getState(InputStream in){
        ArrayList<State> states = new ArrayList<State>();
        try {
            InputStreamReader is = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(is);
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                State current = new State(tokens[3].toUpperCase());
                Hazard h = new Hazard(tokens[4], tokens[5], tokens[0].substring(0, 4), tokens[0].substring(4), tokens[1],
                        tokens[6], tokens[7]);
                int index = states.indexOf(current);
                if (index < 0) {
                    current.addHazard(h);
                    states.add(current);
                } else {
                    states.get(index).addHazard(h);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return states;
    }

    /**
     * Reads events_sorted.txt and organize data based on categories
     * @param in events_sorted.txt
     * @return HashMap of String keys and ArrayList of Strings
     */
    public static HashMap<String, ArrayList<String>> getCategory(InputStream in){
        HashMap<String, ArrayList<String>> weatherGrouping = new HashMap<String, ArrayList<String>>();
        try {
            InputStreamReader is = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(is);
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null){
                ArrayList<String> events = new ArrayList<String>();
                String[] tokens = line.split(",");
                String category = tokens[0];
                for (int i = 1; i < tokens.length; i++){
                    events.add(tokens[i]);
                }
                weatherGrouping.put(category, events);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return weatherGrouping;
    }

    /**
     * Set longitude and latitude of states
     * @param in states_position.csv
     * @param states ArrayList of States
     */
    public static void getStatePosition(InputStream in, ArrayList<State> states) {
        try {
            InputStreamReader is = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(is);
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                State current = new State(tokens[0].toUpperCase());
                int index = states.indexOf(current);
                if (index >= 0) {
                    states.get(index).setLat(Double.parseDouble(tokens[2]));
                    states.get(index).setLon(Double.parseDouble(tokens[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reading input and adding nodes to graph
     * @param in state_adjacency.csv
     * @param states Add the nodes/edges to graph
     * @return Graph, else null.
     */
    public static Graph getGraph(InputStream in, ArrayList<State> states) {
        try {
            InputStreamReader is = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(is);
            String line;
            Graph graph = new Graph();
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] vals = line.toUpperCase().split(",");
                State a = new State(vals[0]);
                State b = new State(vals[1]);
                if(states.indexOf(a) < 0 || states.indexOf(b) < 0) {
                    //`
                }else {
                    graph.add(states.get(states.indexOf(a)),states.get(states.indexOf(b)));
                }
            }
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
