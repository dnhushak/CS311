package hw3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class QuickSort<E extends Comparable<? super E>> implements
		SortAnalysis<E> {

	InsertionSort<E> insertionSorter;

	public QuickSort() {
		insertionSorter = new InsertionSort<E>();
	}

	public void sort(ArrayList<E> arr, int ps) {
		if (ps < 2) {
			throw new IllegalArgumentException("Value of ps is too small");
		}

		// Initialize Stack
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		stack.push(arr.size() - 1);

		while (!stack.empty()) {
			int last = stack.pop();
			int first = stack.pop();

			if (first >= last) {// already sorted
				continue;// next iteration
			}
			// If the size of subarray is less than ps, insertion sort the
			// subarray
			if (last - first < ps) {
				insertionSorter.sort(arr, first, last);
				continue;
			}
			int mid = this.partition(arr, first, last, ps);
			// push both parts onto stack
			stack.push(mid + 1);// first
			stack.push(last);// last
			stack.push(first);// first
			stack.push(mid);// last
		}
	}

	private int partition(ArrayList<E> arr, int first, int last, int ps) {
		if (ps < 2) {
			throw new IllegalArgumentException("ps must be greater than 2");
		}
		if (first < 0) {
			throw new IllegalArgumentException("First must not be negative");
		}
		if (last >= arr.size()) {
			throw new IllegalArgumentException(
					"Last must be less than array length");
		}
		if (last - first + 1 < ps) {
			throw new IllegalArgumentException(
					"Size of subarray must be greater than ps");
		}

		// Select random sub-array to InsertionSort and select pivot
		Random rnd = new Random();
		int start = rnd.nextInt(last - first - (ps - 1)) + first;
		int end = start + ps - 1;
		insertionSorter.sort(arr, start, end);
		E pivot = arr.get((start + end) / 2);

		// Start at ends of subarray
		int left = first;
		int right = last;

		while (true) {
			// Find first left-side number greater than pivot
			while (arr.get(left).compareTo(pivot) < 0)
				left++;
			// Find first right-side number less than pivot
			while (arr.get(right).compareTo(pivot) >= 0)
				right--;
			if (left < right) {
				// Swaps the two out of place numbers
				E swap = arr.get(left);
				arr.set(left, arr.get(right));
				arr.set(right, swap);
				left++;
				right--;
			} else
				break;
		}
		// Return the midpoint
		if (right < first) {
			throw new RuntimeException("Mid cannot be less than first");
		}

		if (right == last) {
			right--;
		}
		return right;
	}

	@Override
	public int analyzeSort(ArrayList<E> list) {
		if (list == null) {
			throw new IllegalArgumentException("Array is null");
		}
		if (list.size() == 0) {
			throw new IllegalArgumentException("Array has no contents");
		}
		// Start time
		long startTime = System.nanoTime();
		// Sort the thing
		this.sort(list, 70);
		// End time
		long endTime = System.nanoTime();
		// Return total time
		return (int) (endTime - startTime) / 1000;
	}

}
