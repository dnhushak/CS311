package hw3;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author Darren Hushak
 */

public class SorterTest {
	InsertionSort<Integer> sort = new InsertionSort<Integer>();
	ArrayList<Integer> vals = new ArrayList<Integer>(Arrays.asList(80, 120, 80,
			110, 60, 1, 100));
	ArrayList<Integer> valsSorted = new ArrayList<Integer>(Arrays.asList(1, 60,
			80, 80, 100, 110, 120));
	ArrayList<Integer> valsSubSorted = new ArrayList<Integer>(Arrays.asList(80,
			80, 110, 120, 60, 1, 100));
	ArrayList<Integer> valsNeg = new ArrayList<Integer>(Arrays.asList(-80, 120,
			80, 110, 60, 1, 100));
	ArrayList<Integer> valsEmpty = new ArrayList<Integer>();


	// /////////////////////////////////////////////////////////
	//
	//
	// InsertionSort Tests
	//
	//
	// /////////////////////////////////////////////////////////

	// Test for actual sorting accuracy
	@Test
	public void analyzeSortTest() {
		System.out.println(sort.analyzeSort(vals));
		assertThat(vals, is(valsSorted));
	}

	// Null test
	@Test(expected = NullPointerException.class)
	public void insertionNullTest() {
		sort.analyzeSort(null);
	}

	// Non-null but empty array test
	@Test(expected = IllegalArgumentException.class)
	public void insertionEmptyTest() {
		sort.analyzeSort(valsEmpty);
	}

}