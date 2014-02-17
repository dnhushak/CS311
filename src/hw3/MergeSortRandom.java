package hw3;

import java.util.ArrayList;
import java.util.Random;

public class MergeSortRandom {
	public static void main(String[] args) {
		// Random Number generator
		Random rnd = new Random();
		// The Sorter
		MergeSort<Integer> sorter = new MergeSort<Integer>();
		// What size of array to start with
		int sizeStart = 100;
		// Size of array to end with
		int sizeEnd = 10000;
		// How to increment the arrays
		int increment = 100;
		// Number of runs to average (Smooths out the outliers
		int averages = 3;
		// The array to keep track of the running times
		int[] times = new int[sizeEnd];

		// Run averages times to average out the outliers
		for (int k = 0; k < averages; k++) {
			for (int i = sizeStart; i < sizeEnd; i += increment) {
				// Create new array to be sorted
				ArrayList<Integer> arr = new ArrayList<Integer>();
				// Add new elements
				for (int j = i; j > 0; j--) {
					// Adds new elements in decreasing order (worst case for
					// Quick sort)
					// arr.add(j);

					// Adds random elements
					arr.add(rnd.nextInt());
				}

				// Sort and return the time
				int newTime = sorter.analyzeSort(arr);

				// If first run, set the time to the actual sorting time, else
				// check for outlier
				if (k > 0) {
					// If the new time is 1.5 times the size of the already
					// captured times, kick it out
					if (newTime < (1.5 * times[i] / k)) {
						times[i] += newTime;
					} else {
						times[i] += times[i];
					}
				} else {
					// First run
					times[i] = newTime;
				}
			}
		}
		// Print out the times in a comma separated format for graphing
		for (int m = sizeStart; m < sizeEnd; m += increment) {
			System.out.println(m + ", " + (times[m] / averages));
		}

	}
}
