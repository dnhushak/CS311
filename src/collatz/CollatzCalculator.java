package collatz;

import java.util.ArrayList;

public class CollatzCalculator {
	ArrayList<Integer> chain;
	int start;

	public CollatzCalculator(int initInt) {
		chain = new ArrayList<Integer>();
		start = initInt;
	}

	public ArrayList<Integer> CollatzChain(int newInt)
			throws IllegalArgumentException {
		if (newInt < 1) {
			throw new IllegalArgumentException("Must be a postitive integer");
		}
		chain.add(newInt);
		while (newInt != 1) {
			if (isEven(newInt)) {
				newInt = even(newInt);
			} else {
				newInt = odd(newInt);
			}
			chain.add(newInt);
		}
		return chain;
	}

	private int odd(int odd) {
		return (odd * 3) + 1;
	}

	private int even(int even) {
		return even / 2;
	}

	private boolean isEven(int check) {
		return (check % 2 == 0);
	}
}
