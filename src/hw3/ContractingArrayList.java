//Problem 6
/*
 * Notes:
 * Assuming Add method only adds to the end of the array, 
 * and assuming Remove method only removes the last object of the array
 * 
 * Also ignoring the inevitable breaking of the contraction rules when the array is of size 0:
 * Would technically need to have capacity of 0 as well (twice nothing is still nothing).
 * As this is an edge case and we're concerned with large n, this bit of specifics matters little,
 * and I will just initialize the array to have a capacity of 1.
 */

public class ContractingArrayList<K> {
	// Actual size of array
	int capacity;
	// Number of non-null elements in the array
	int size;
	// Data in array list
	K[] data;

	ContractingArrayList() {
		this.size = 0;
		this.capacity = 1;
		// Generic syntax is a little off, but semi-pseudo-coding
		data = new K[this.capacity];
	}

	public void Add(K element) {
		// Check if the array is at capacity
		if (size == capacity) {
			// Double the capacity
			this.capacity *= 2;
			// See above re: generic syntax
			K[] newdata = new K[this.capacity];

			// Copy array over from old data. Takes O(capacity) time, but only
			// occurs every 1/capacity, therefore amortized to O(1)
			for (int i = 0; i < this.size; i++) {
				newdata[i] = this.data[i];
			}
			// Point data to the correct spot
			this.data = newdata;
		}
		// Add into the array, takes O(1) time
		this.data[size] = element;
		// Increment size counter
		this.size++;
	}

	public void Remove() {
		// Check if the array is at capacity
		if (size == capacity / 2) {
			// Halve the capacity
			this.capacity /= 2;
			// See above re: generic syntax
			K[] newdata = new K[this.capacity];

			// Copy array over from old data. Takes O(capacity) time, but only
			// occurs every 1/capacity, therefore amortized to O(1)
			for (int i = 0; i < this.size; i++) {
				newdata[i] = this.data[i];
			}
			// Point data to the correct spot
			this.data = newdata;
		}
		// Add into the array, takes O(1) time
		this.data[capacity] = null;
		// Decrement size counter
		this.size--;
	}
}
