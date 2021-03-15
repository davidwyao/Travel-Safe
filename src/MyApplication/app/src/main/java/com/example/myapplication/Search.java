package com.example.myapplication;

import java.util.ArrayList;

/**
 * Search
 */
public class Search {

    /**
     * Search constructor
     * @param arr Array to search
     * @param month Month
     * @return ArrayList of hazards from low to high index
     */
    public static ArrayList<Hazard> search(ArrayList<Hazard> arr, int month) {
        int index = binarySearch(arr, month, 0, arr.size()-1);
        if(index<0)
            return null;
        int lo = index;
        int hi = index;
        while(true) {
            if(hi+1>=arr.size())
                break;
            if(arr.get(hi+1).month()!=month) {
                break;
            }else
                hi++;
        }
        while(true) {
            if(lo-1<0)
                break;
            if(arr.get(lo-1).month()!=month) {
                break;
            }else
                lo--;
        }
        return new ArrayList<Hazard>(arr.subList(lo, hi+1));
    }

    /**
     * Binary Search
     * @param arr Array to search
     * @param month Month
     * @param lo Low Index
     * @param hi High Index
     * @return Index of array equivalent to month
     */
    public static int binarySearch(ArrayList<Hazard> arr, int month, int lo, int hi) {
        if (lo > hi)
            return -1;
        int mid = (lo + hi) / 2;
        if (arr.get(mid).month() == month)
            return mid;
        else if (arr.get(mid).month() > month)
            return binarySearch(arr, month, lo, mid - 1);
        else
            return binarySearch(arr, month, mid + 1, hi);
    }

}
