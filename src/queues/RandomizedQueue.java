package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * 
 * Performance requirements.  
 * 
 * Your randomized queue implementation must support each randomized queue 
 * operation (besides creating an iterator) in constant amortized time. 
 * That is, any intermixed sequence of m randomized queue operations 
 * (starting from an empty queue) must take at most cm steps in the worst case, 
 * for some constant c. 
 * 
 * < iterator's construction cost time! keyword "amortized" implies that 
 * < we may use resizing-array implementation better. and use linked-list
 * < implementation for randomized may cost linear time: we have to traverse 
 * < from the our begining node in the list.
 * 
 * A randomized queue containing n items must use at most 
 * 48n + 192 bytes of memory. 
 * 
 * Additionally, your iterator implementation must support operations next() 
 * and hasNext() in constant worst-case time; and construction in linear time; 
 * you may (and will need to) use a linear amount of extra memory per iterator.
 * 
 * < iterator's construction take linear time! and may need O(n) extra memory
 * 
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] rq;	
	private int head;
	private int tail;
	private int n;
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		rq = (Item[]) new Object[1];
		head = 0;
		tail = 0;
		n = 0;
	}
	
	// is the randomized queue empty?
	public boolean isEmpty() {
		return n == 0;
	}
	
	// return the number of items on the randomized queue
	public int size() {
		return n;
	}
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			copy[i] = rq[(head + i) % rq.length];
		}
		rq = copy;
		head = 0;
		tail = n;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new IllegalArgumentException();
		if (n == rq.length) resize(2 * rq.length);
		rq[tail] = item;
		tail = (tail + 1) % rq.length;
		n++;
	}
	
	private int getRandomIdx() {
		return (head + StdRandom.uniform(n)) % rq.length;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		int randomIdx = getRandomIdx();
		Item item = rq[randomIdx];
		rq[randomIdx] = rq[head];
		rq[head] = null;
		head = (head + 1) % rq.length;
		n--;
		if (n > 0 && n == rq.length/4) resize(rq.length/2);
		return item;
	}
	
	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty()) throw new NoSuchElementException();
		return rq[getRandomIdx()];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		/**
		 * Iterator.  Each iterator must return the items in uniformly random order. 
		 * The order of two or more iterators to the same randomized queue must be 
		 * mutually independent; each iterator must maintain its own random order.
		 */
		return new RandomIterator();
	}
	
	private class RandomIterator implements Iterator<Item> {
		
		private int[] randomIdxs;
		private int i;
		
		public RandomIterator() {
			
			randomIdxs = new int[n];
			for (int j = 0; j < n; j++) {
				randomIdxs[j] = j;
			}
			StdRandom.shuffle(randomIdxs);

			i = 0;
		}
		
		public boolean hasNext() {
			return i != randomIdxs.length;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return rq[(head + randomIdxs[i++]) % rq.length];
		}
		
	}
	
	// unit testing (required)
	public static void main(String[] args) {
		/**
		 * Unit testing.  
		 * 
		 * Your main() method must call directly every public 
		 * constructor and method to verify that they work as prescribed 
		 * (e.g., by printing results to standard output).
		 */
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		for (int i = 0; i < 5; i++) {
			rq.enqueue(i);
		}
		Iterator<Integer> it = rq.iterator();
		while (it.hasNext()) StdOut.print(it.next() + " ");
		StdOut.println(" size: " + rq.size());
		
		it = rq.iterator();
		while (it.hasNext()) StdOut.print(it.next() + " ");
		StdOut.println(" size: " + rq.size());
		
		for (int a : rq) {
			for (int b : rq) {
				StdOut.print(a + "-" + b + " ");
			}
			StdOut.println();
		}
		
		for (int i = 0; i < 5; i++) {
			StdOut.println("Dequeue: " + rq.dequeue());
			
			if (i < 3) {
				it = rq.iterator();
				while (it.hasNext()) StdOut.print(it.next() + " ");
				StdOut.println(" size: " + rq.size());
			}
		}
		
		StdOut.println("Queue empty? " + rq.isEmpty());
		
		for (int i = 0; i < 5; i++) {
			rq.enqueue(i);
		}
		
		int[] count = new int[5];
		
		for (int i = 0; i < 10000; i++) {
			count[rq.sample()]++;
		}
		
		for (int i = 0; i < 5; i++) {
			StdOut.print(count[i] + " ");
		}
		
	}
	
}
