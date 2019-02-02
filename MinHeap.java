import java.util.Arrays;

/**
 * The main part of the MinHeap is an array for which the following condition for the i-th element is true:
 * (2*i+1 < size) => (array[i] < array[2*i+1]) and (2*i+2 < size) => (array[i] < array[2*i+2]). The element array[2*i+1] is
 * the left child of array[i] and array[2*i+2] is the right child, if the array is considered as a tree.
 * 
 * This class supports 2 types of construction of the heap.
 * 1. constructor just defines a maxumum size for the array. when using this constructor, then one can insert element
 * after element in the heap with the insert(int x) method.
 * 
 * 2. constructor takes an array as an input an references this.heap to the input array and transforms it into a MinHeap. 
 * 
 * Note: This class can be easily transformed into a MaxHeap by modifying the heapifyUpwards and heapify method. Just replace
 * the < with > in the right places.
 * 
 * @author Said Elbiev
 * @version 1.0
 *
 */
public class MinHeap
{
	public int[] heap;	//the actual heap
	public int size;	//effective number of elements in the heap
	
	public MinHeap(int MaxSize)
	{
		heap = new int[MaxSize];
		size = 0;
	}
	
	public MinHeap(int[] array)
	{
		this.heap = array;
		size = array.length;
		buildHeap();
	}
	
	public void insert(int x)
	{
		if(size>=heap.length-1)
			throw new RuntimeException("Heap is already full");
		
		size++;
		heap[size-1] = x;
		heapifyUpwards(size-1);
	}
	
	/**
	 * deletes the top element of the heap. the free position at index 0 will be filled with the last element
	 * and after that, the heap condition will be restored from 0 to size-1
	 */
	public void deleteTopElement()
	{
		if(size==0)
			return;
		heap[0] = heap[size-1];
		heap[size-1] = 0;
		size--;
		heapify(heap,0,size-1);
	}
	
	/**
	 * deletes the element at given index. the free position at index i will be filled with the last element
	 * and after that, the heap condition will be restored from i to size-1
	 */
	public void deleteElementAt(int i)
	{
		if(size==0)
			return;
		heap[i] = heap[size-1];
		heap[size-1] = 0;
		size--;
		heapify(heap,i,size-1);
	}
	
	public void heapifyUpwards(int i)
	{
		if(i==0)
			return;
		int parent = (i-1)/2;
		while(i > 0 && heap[i]<heap[parent])
		{
			int tmp = heap[i];
			heap[i] = heap[parent];
			heap[parent] = tmp;
			
			i = parent;
			parent = (i-1)/2;
		}
	}

	/**
	 * transforms the array into a MinHeap
	 */
	private void buildHeap() {
		for (int i = heap.length/2; i >= 0; i--) {
			heapify(heap, i,size);
		}
	}

	/**
	 * restores the heap condition from i to n for a given array. 
	 * this method can also used for other arrays than for the local heap
	 */
	public void heapify(int[] array, int i, int n) {
		
		int j = 2*i+1;	//j corresponds to the left child of the current position i
		
		if(j > n)	//continue only if there is left child of i
			return;
		
		
		if(j+1<n)		//if i has a right child...
		{
			if(array[j+1] < array[j])	//and this child is greater than the left child
				j++;	//set this as the successor of i
		}	//after each iteration of the heapify method, we will update the child to the greater child
		
		while(array[i] > array[j])
		{
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
			
			i = j;
			if(2*i+1 < n)	//after each swap, we will do what we did before the while loop: 
							//updating position i and defining j, the greater successor of i
			{
				j = 2*i+1;
				if(j+1<n)
				{
					if(array[j+1]<array[j])
						j++;
				}
			}
			else
				break;		//now we have reached the last element, which has no children
		}
	}
	
	public String toString()
	{
		return Arrays.toString(heap);
	}
}