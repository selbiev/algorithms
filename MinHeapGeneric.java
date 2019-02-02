import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a generalized version of the MinHeap for a generic type T. In a separate class, we already created the MinHeap for 
 * Integers. The main part of the MinHeap is an ArrayList of type T for which the following condition for the i-th element is true:
 * (2*i+1 < size) => (array[i] < array[2*i+1]) and (2*i+2 < size) => (array[i] < array[2*i+2]) where with < we mean the order
 * with respect to the relation in which the 2 objects of T are with. Furthermore, we have written array[i] instead of
 * arraylist.get(i) just for convenience. The element array[2*i+1] is the left child of array[i] and array[2*i+2] 
 * is the right child, if the array is considered as a tree.
 * 
 * In contrast to the Integer-MinHeap, this class only supports 1 constructor, namely one without any parameters. The heap must
 * be built successively by inserting elements.
 * 
 * Note: This class can be easily transformed into a MaxHeap by modifying the heapifyUpwards and heapify method. Just replace
 * the < with > in the right places.
 * 
 * @author Said Elbiev
 * @version 1.0
 *
 */
public class MinHeapGeneric<T extends Comparable<T>>
{
	public ArrayList<T> heap;	//the actual heap
	
	public MinHeapGeneric()
	{
		heap = new ArrayList<T>();
	}

	public void insert(T x)
	{
		heap.add(x);
		heapifyUpwards();
	}
	
	/**
	 * deletes the top element of the heap. the free position at index 0 will be filled with the last element
	 * and after that, the heap condition will be restored from 0 to size-1
	 */
	public void deleteTopElement()
	{
		if(heap.size()==0)
			return;
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		heapify(heap,0,heap.size()-1);
	}
	
	/**
	 * deletes the element at given index. the free position at index i will be filled with the last element
	 * and after that, the heap condition will be restored from i to size-1
	 */
	public void deleteElementAt(int i)
	{
		if(heap.size()==0)
			return;
		heap.set(i, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		heapify(heap,i,heap.size()-1);
	}
	
	public void heapifyUpwards()
	{
		if(heap.size()==0)
			return;
		else if(heap.size()==1)
			return;
		int i = heap.size()-1;
		int parent = (i-1)/2;
		while(i > 0 && heap.get(i).compareTo(heap.get(parent))==-1)
		{			
			T tmp = heap.get(i);
			heap.set(i, heap.get(parent));
			heap.set(parent, tmp);
			
			i = parent;
			parent = (i-1)/2;
		}
	}

	/**
	 * restores the heap condition from i to n for a given array. 
	 * this method can also used for other arrays than for the local heap
	 */
	public void heapify(ArrayList<T> array, int i, int n) {
		
		int j = 2*i+1;	//j corresponds to the left child of the current position i
		
		if(j > n)	//continue only if there is left child of i
			return;
		
		
		if(j+1<n)		//if i has a right child...
		{
			if(array.get(j+1).compareTo(array.get(j)) == -1) //and this child is greater than the left child
				j++;	//set this as the successor of i	
		}	//after each iteration of the heapify method, we will update the child to the greater child
		
		while(array.get(i).compareTo(array.get(j)) == 1)
		{
			T tmp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, tmp);
			
			i = j;
			if(2*i+1 < n)	//after each swap, we will do what we did before the while loop: 
							//updating position i and defining j, the greater successor of i
			{
				j = 2*i+1;
				if(j+1<n)
				{
					if(array.get(j+1).compareTo(array.get(j)) == -1)
						j++;
				}
			}
			else
				break;		//now we have reached the last element, which has no children
		}
	}
	
	public String toString()
	{
		return heap.toString();
	}
}