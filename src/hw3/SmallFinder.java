//Problem 3

// Pseudo code, so generic type erasure and all that is obviously wrong
public class SmallFinder<D> {

	static D FindKthSmallestItem(int k, D[] data) {
		MaxHeap<D> Heap = new MaxHeap<D>;
		
		// Start out with the first k values of the list
		for (int i =0; i<k;i++){	// O(k)*{
			Heap.add(data[i]);		// 		O(1)
			Heap.PercolateDown();	// 		O(log(i))}
		}
		
		// From this point forward, Heap.getMax() returns the kth smallest object
		
		// We keep the maxheap of size k, 
		// insuring that the maximum value is always largest of the k number of smallest items,
		// and is thus the kth smallest item.
		
		// For each item, check if it is smaller than the maximum value of the heap. 
		// If it is, then the maximum is no longer the kth smallest item, but rather the k+1th smallest item.
		// We must then remove this old maximum in lieu of the new maximum
		// We then re-heapify the heap by percolating down, to ensure that the maximum is always at the top
		for (int j=k;j<data.size;j++){		// O(n-k)*{
			if (data[j] < Heap.getMax()){		// O(1), max of a max heap is the first item
				Heap.Set(max_index, data[j]); 	// Remove the max value, replacing it with the new data value
				Heap.PercolateDown();			// O(log k)}
			}
		}
		return Heap.getMax();					// O(1)
		
		
		// In the end, we end up with O(n log(k)) for absolute worst case
		// Assuming k is sufficiently less than n, the heap stays quite small, and rather quick.
		// Because of this, the log(k) is a much smaller factor than O(n), and the average runtime
		// looks to be more like O(n)
		
		// Also, an interesting note, if the data size is known (in this case it is assumed to be), 
		// and k > n/2, we can flip the logic and look for the (n - k)th largest object. Because the data size
		// is known, this is equivalent to the nth smallest object.
		// From here, we change:
		//
		// MaxHeap 			-> MinHeap
		// GetMax() 		-> GetMin()
		// (data < Heap...) -> (data > Heap...)
		
		// This keeps the upper bound of absolute worst case to O(n log (n/2)), when k = n/2, any higher or lower and the log would be lower
		// In terms of O(), this isn't much of an improvement, but for average time, it certainly is an optimization
		

	}
}
