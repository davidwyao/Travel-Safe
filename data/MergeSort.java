package test;

import java.util.ArrayList;


/**
 * MergeSort on ArrayLists of Comparable objects.
 * 
 * @author David Yao
 * @version April 10 2020
 */
public class MergeSort {
	private static ArrayList<Comparable> aux; // temporary for merges

	/**
	 * Sorts the ArrayList, changing values directly.
	 * 
	 * @param array ArrayList to be sorted.
	 */
	public static void sort(ArrayList<Comparable> array) {
		aux = new ArrayList<Comparable>(array.size());
		sort(array, 0, array.size() - 1);
	}

	/**
	 * Merge the sorted values back into the given array.
	 * 
	 * @param array Array to merge into.
	 * @param lo Low index.
	 * @param mid Middle index.
	 * @param hi High index.
	 */
	public static void merge(ArrayList<Comparable> array, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++)
			aux.set(k, array.get(k));
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				array.set(k, aux.get(j++));
			else if (j > hi)
				array.set(k, aux.get(i++));
			else if (aux.get(j).compareTo(aux.get(i)) < 0)
				array.set(k, aux.get(j++));
			else
				array.set(k, aux.get(i++));
		}
	}

	private static void sort(ArrayList<Comparable> array, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(array, lo, mid);
		sort(array, mid + 1, hi);
		merge(array, lo, mid, hi);
	}
}
