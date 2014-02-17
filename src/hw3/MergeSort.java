package hw3;

import java.util.ArrayList;

public class MergeSort<E extends Comparable<? super E>> implements
		SortAnalysis<E> {
	// Scratch work array, for the merge method
	ArrayList<E> scratch = new ArrayList<E>();

	public void sort(ArrayList<E> arr, int first, int last) {

		// If first>=last, then arr is sorted
		if (first < last) {
			// Get midpoint
			int mid = first + (last - first) / 2;
			// Recursive call for left side
			this.sort(arr, first, mid);
			// Recursive call for right side
			this.sort(arr, mid + 1, last);
			// Combine them both
			this.merge(arr, first, mid, last);
		}

	}

	private void merge(ArrayList<E> arr, int first, int mid, int last) {

		// Copy from first to last into scratch array
		int l;
		for (l = first; l <= last; l++) {
			scratch.set(l, (arr.get(l)));
		}

		int i = first;
		int j = mid + 1;
		int k = first;
		// Grab the smallest value and spit it back to original array
		while (i <= mid && j <= last) {
			if (scratch.get(i).compareTo(scratch.get(j)) <= 0) {
				arr.set(k, scratch.get(i));
				i++;
			} else {
				arr.set(k, scratch.get(j));
				j++;
			}
			k++;
		}
		// Copy the rest of the left side of the scratch into the original array
		while (i <= mid) {
			arr.set(k, scratch.get(i));
			k++;
			i++;
		}

	}

	@Override
	public int analyzeSort(ArrayList<E> list) {
		if (list == null) {
			throw new IllegalArgumentException("Array is null");
		}
		if (list.size() == 0) {
			throw new IllegalArgumentException("Array has no contents");
		}
		// Copy input array to scratch arraylist
		scratch.addAll(list);
		// Start time
		long startTime = System.nanoTime();
		// Sort the thing
		this.sort(list, 0, list.size() - 1);
		// End time
		long endTime = System.nanoTime();
		// Return total time
		return (int) (endTime - startTime) / 1000;
	}

}
