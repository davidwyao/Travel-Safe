package com.example.myapplication;

import java.util.ArrayList;

/**
 * State Class
 */
public class State {

    private String name;
    private ArrayList<Hazard> hazards;
    private boolean dataSorted = false;
    private int[] monthlyHazardNum;
    private double lat, lon;

    /**
     * Constructor for states
     * @param name Name of state
     */
    public State(String name) {
        this.name = name;
        this.hazards = new ArrayList<Hazard>();
        monthlyHazardNum = new int[12];
    }

    /**
     * Returns the state name
     * @return State name
     */
    public String name() {
        return this.name;
    }

    /**
     * Setter for latitude
     * @param l Latitude to be set
     */
    public void setLat(double l) {
        this.lat = l;
    }

    /**
     * Setter for longitude
     * @param l Longitude to be set
     */
    public void setLon(double l) {
        this.lon = l;
    }

    /**
     * Getter for latitude
     * @return Latitude
     */
    public double lat() {
        return lat;
    }

    /**
     * Getter for longitude
     * @return longitude
     */
    public double lon() {
        return lon;
    }

    /**
     * Adding hazards to state
     * @param h Hazard object
     */
    public void addHazard(Hazard h) {
        this.hazards.add(h);
        monthlyHazardNum[h.month() - 1]++;
    }

    /**
     * Returning number of hazards
     * @param month Month
     * @return Number of hazards in month
     */
    public int MonthlyHazardNum(int month) {
        return monthlyHazardNum[month - 1];
    }

    /**
     * checks if object b is the same as current State object
     * @param b
     * @return
     */
    @Override
    public boolean equals(Object b) {
        return ((State) b).name().equals(name());
    }

    /**
     * Return Hash Code
     * @return Hash Code
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Return the name of the State
     * @return Name of the State
     */
    public String toString() {
        return this.name();
    }

    /**
     * Return hazards in State in a specific month
     * @param month Month
     * @return ArrayList of hazards in the month in state
     */
    public ArrayList<Hazard> searchByMonth(int month) {
        /**
         * Sort the hazards using mergesort
         */
        if (!dataSorted) {
            MergeSort.sort(hazards);
        }
        return Search.search(hazards, month);
    }

    /**
     * Get the Top 3 months according to the frequency of hazards in month in state
     * @return ArrayList of months
     */
    public ArrayList<Integer> getTop3() {
        ArrayList<Integer> out = new ArrayList<Integer>();
        IndexMinPQ<Integer> pq = new IndexMinPQ<Integer>(monthlyHazardNum.length);
        for (int i = 0; i < this.monthlyHazardNum.length; i++) {
            pq.insert(i, monthlyHazardNum[i]);
        }
        for (int i = 0; i < 3; i++) {
            out.add(pq.delMin()+1);
        }
        return out;
    }

}
