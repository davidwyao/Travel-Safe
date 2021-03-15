package com.example.myapplication;

/**
 * Hazard Class
 */
public class Hazard implements Comparable<Hazard>{
    private final String name;
    private final String magnitude;
    private final String month, year, day;
    private final String lat, lon;

    /**
     * Hazard Constructor
     * @param name Name of Hazard
     * @param magnitude Magnitude of Hazard
     * @param year Year of Hazard
     * @param month Month of Hazard
     * @param day Day of Hazard
     * @param lat Longitude of Hazard
     * @param lon Latitude of Hazard
     */
    public Hazard(String name, String magnitude, String year, String month, String day, String lat, String lon) {
        this.name = name;
        this.magnitude = magnitude;
        this.month = month;
        this.year = year;
        this.day = day;
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Getter for Name of Hazard
     * @return Hazard name
     */
    public String name() {
        return name;
    }

    /**
     * Getter for magnitude of Hazard
     * @return Magnitude of Hazard
     */
    public String magnitude() {
        return magnitude;
    }

    /**
     * Getter for month of Hazard
     * @return Month of Hazard
     */
    public int month() {
        return Integer.parseInt(month);
    }

    /**
     * Getter for year of hazard
     * @return Year of hazard
     */
    public String year() {
        return year;
    }

    /**
     * Getter for day of hazard
     * @return Day of hazard
     */
    public String day() {
        return day;
    }

    /**
     * Getter for latitude of hazard
     * @return Latitude of hazard
     */
    public String lat() {
        return lat;
    }

    /**
     * Getter for longitude of hazard
     * @return Longitude of hazard
     */
    public String lon() {
        return lon;
    }


    /**
     * Compare hazard to other hazard
     * @param other Hazard object
     * @return True if month of hazard of other object is the same as current month of hazard. False otherwise.
     */
    public int compareTo(Hazard other) {
        Integer a =  month();
        return a.compareTo(other.month());
    }
}
