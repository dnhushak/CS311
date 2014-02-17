package hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author Darren Hushak
 */

public class SorterTest {
	int largeSize = 9929;
	ArrayList<Integer> vals;
	ArrayList<Integer> valsSorted;
	ArrayList<Integer> valsSubSorted;
	ArrayList<Integer> valsNeg;
	ArrayList<Integer> valsEmpty;
	ArrayList<Integer> large;
	ArrayList<Integer> largeSorted;
	InsertionSort<Integer> Isort;
	QuickSort<Integer> Qsort;
	MergeSort<Integer> Msort;

	@Before
	public void setup() {
		Isort = new InsertionSort<Integer>();
		Qsort = new QuickSort<Integer>();
		Msort = new MergeSort<Integer>();
		vals = new ArrayList<Integer>(Arrays.asList(80, 120, 80, 110, 60, 1,
				100));
		valsSorted = new ArrayList<Integer>(Arrays.asList(1, 60, 80, 80, 100,
				110, 120));
		valsSubSorted = new ArrayList<Integer>(Arrays.asList(80, 80, 110, 120,
				60, 1, 100));
		valsNeg = new ArrayList<Integer>(Arrays.asList(-80, 120, 80, 110, 60,
				1, 100));
		valsEmpty = new ArrayList<Integer>();
		large = new ArrayList<Integer>();
		largeSorted = new ArrayList<Integer>();
		Random rnd = new Random();

		int i;
		for (i = 0; i < largeSize; i++) {
			large.add(rnd.nextInt());
		}
		largeSorted.addAll(large);
		Collections.sort(largeSorted);
	}

	// /////////////////////////////////////////////////////////
	//
	//
	// InsertionSort Tests
	//
	//
	// /////////////////////////////////////////////////////////

	// Test for actual sorting accuracy
	@Test
	public void IanalyzeSortTest() {
		System.out.println("Insertion sort for 7: " + Isort.analyzeSort(vals));
		assertThat(vals, is(valsSorted));
	}

	// Test for actual sorting accuracy
	@Test
	public void IanalyzeSortTestLarge() {
		System.out.println("Insertion sort for " + largeSize + ": "
				+ Isort.analyzeSort(large));
		assertThat(large, is(largeSorted));
	}

	// Null test
	@Test(expected = IllegalArgumentException.class)
	public void insertionNullTest() {
		Isort.analyzeSort(null);
	}

	// Non-null but empty array test
	@Test(expected = IllegalArgumentException.class)
	public void insertionEmptyTest() {
		Isort.analyzeSort(valsEmpty);
	}

	// /////////////////////////////////////////////////////////
	//
	//
	// Quicksort Tests
	//
	//
	// /////////////////////////////////////////////////////////

	// Test for actual sorting accuracy
	@Test
	public void QanalyzeSortTest() {
		System.out.println("Quick sort for 7: " + Qsort.analyzeSort(vals));
		assertThat(vals, is(valsSorted));
	}

	// Test for actual sorting accuracy
	@Test
	public void QanalyzeSortTestLarge() {
		System.out.println("Quick sort for " + largeSize + ": "
				+ Qsort.analyzeSort(large));
		assertThat(large, is(largeSorted));
	}

	// Null test
	@Test(expected = IllegalArgumentException.class)
	public void QuickNullTest() {
		Qsort.analyzeSort(null);
	}

	// Non-null but empty array test
	@Test(expected = IllegalArgumentException.class)
	public void QuickEmptyTest() {
		Qsort.analyzeSort(valsEmpty);
	}

	// /////////////////////////////////////////////////////////
	//
	//
	// Merge Tests
	//
	//
	// /////////////////////////////////////////////////////////

	// Test for actual sorting accuracy
	@Test
	public void ManalyzeSortTest() {
		System.out.println("Merge sort for 7: " + Msort.analyzeSort(vals));
		assertThat(vals, is(valsSorted));
	}

	// Test for actual sorting accuracy
	@Test
	public void ManalyzeSortTestLarge() {
		System.out.println("Merge sort for " + largeSize + ": "
				+ Msort.analyzeSort(large));
		assertThat(large, is(largeSorted));
	}

	// Null test
	@Test(expected = IllegalArgumentException.class)
	public void MergeNullTest() {
		Msort.analyzeSort(null);
	}

	// Non-null but empty array test
	@Test(expected = IllegalArgumentException.class)
	public void MergeEmptyTest() {
		Msort.analyzeSort(valsEmpty);
	}

}