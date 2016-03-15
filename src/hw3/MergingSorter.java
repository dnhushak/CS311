
//Problem 2

//Pseudo-code, so the generics are obviously wrong
public class MergingSorter<D> {

	static MergeWithHeaps(int k, D[] data) {

		int n = data.size;
		int bucketSize = n / k;

		// This will be less precise pseudocode than my other problems, but here
		// goes:

		// Make an array of heaps of size k, these will be our bucks
		Heap<D>[] buckets = new Heap<D>[k];

		// Check for < and > operations to determine which bucket each data point goes into
		for (int i = 0; i < data.size; i++) {
			int bucket = SelectBucket(data[i]);
			buckets[bucket].AddAndPercolate(data[i]);
		}
		
		//Go through each of the bucket heaps, 
		//grabbing the minimum value and gradually placing them back into the data array
		int count = 0;
		foreach(bucket){
			foreach(D in bucket){
				data[count] = heap.removeMin();
				count++;
			}
		}

	}

}
