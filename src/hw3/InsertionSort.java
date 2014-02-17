package hw3;

import java.util.ArrayList;

public class InsertionSort<E extends Comparable<? super E>> implements
		SortAnalysis<E> {

	public void sort(ArrayList<E>  arr, int first, int last) {


		// Sort the array. Start at index one, and continually swap that element
		// with the element on its left until it is not less than the element on
		// its left. Move on to the next starting index and continue until
		// sorted.
		for (int i = first + 1; i <= last; i++) {
			//Grab the index's value
			E temp = arr.get(i);
			//Continually decrease index until index's value is not less than the index - 1's value
			for (int j = i - 1; j >= 0 && temp.compareTo(arr.get(j)) < 0; j--) {
				//Swap
				E swap = arr.get(j);
				arr.set(j, arr.get(j+1));
				arr.set(j+1, swap);
			}
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
		//Start time
		long startTime = System.nanoTime();
		//Sort the thing
		this.sort(list, 0, list.size() - 1);
		//End time
		long endTime = System.nanoTime();
		//Return total time
		return (int)(endTime - startTime)/1000;
	}

}
