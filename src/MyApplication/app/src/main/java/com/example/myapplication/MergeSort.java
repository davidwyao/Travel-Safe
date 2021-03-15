package com.example.myapplication;
import java.util.ArrayList;


/**
 * MergeSort on ArrayLists of Comparable objects.
 *
 * @author David Yao
 * @version April 10 2020
 */
public class MergeSort {
    private static Hazard[] aux; // temporary for merges

    /**
     * Sorts the ArrayList, changing values directly.
     * @param array ArrayList to be sorted.
     */
    public static void sort(ArrayList<Hazard> array) {
        aux = new Hazard[array.size()];
        sort(array, 0, array.size() - 1);

    }

    /**
     * Merge the sorted values back into the given array.
     * @param array Array to merge into.
     * @param lo Low index.
     * @param mid Middle index.
     * @param hi High index.
     */
    public static void merge(ArrayList<Hazard> array, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = array.get(k);
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                array.set(k, aux[j++]);
            else if (j > hi)
                array.set(k, aux[i++]);
            else if (aux[j].compareTo(aux[i]) < 0)
                array.set(k, aux[j++]);
            else
                array.set(k, aux[i++]);
        }
    }

    /**
     * Sorting Merge Function
     * @param array Array to merge into
     * @param lo Low index
     * @param hi High index
     */
    private static void sort(ArrayList<Hazard> array, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(array, lo, mid);
        sort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }

}
