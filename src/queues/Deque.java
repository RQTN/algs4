package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * Performance requirements.  
 * 
 * Your deque implementation must support each deque operation 
 * (including construction) in constant worst-case time. 
 * 
 * < This means that only can choose linked-list implementation
 * < Since if use resizing-array implementation, sometimes we need to resize 
 * < the array, some ops like add and remove may not cost constant worst-case time.
 * 
 * A deque containing n items must use at most 48n + 192 bytes of memory. 
 * Additionally, your iterator implementation must support each operation 
 * (including construction) in constant worst-case time.
 *
 * < 48n + 192 bytes and hasNext(), remove(), next() all cost constant worst-case time.
 * < use 48n + 16 + 16 + 4 + 24 + 8 + 4(pad) = 48n + 72 bytes
 */


public class Deque<Item> implements Iterable<Item> {
	// 16(overhead) bytes
	
	private Node first, last;	// 8(ref) + 8(ref) = 16 bytes
	private int n;				// 4(int) bytes
	
	private class Node {
		// 16(overhead) + 8(inner class) = 24 bytes
		Item item;				// 8(ref) bytes
		Node prev;				// 8(ref) bytes
		Node next;				// 8(ref) bytes
		// 24 + 8 + 8 + 8 = 48 bytes -> 48n bytes for n items
	}							
	
	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		n = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return n == 0;
	}
	
	// return the number of items on the deque
	public int size() {
		return n;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) throw new IllegalArgumentException();
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		first.prev = null;
		if (!isEmpty()) {
			oldFirst.prev = first;
		} 
		else {
			last = first;
		}
		n++;
	}
	
	// add the item to the back
	public void addLast(Item item) {
		if (item == null) throw new IllegalArgumentException();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.prev = oldLast;
		if (!isEmpty()) {
			oldLast.next = last;
		}
		else {
			first = last;
		}
		n++;
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		n--;
		if (n == 0) last = first;
		else first.prev = null;
		return item;
	}
	
	// remove and return the item from the back
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = last.item;
		last = last.prev;
		n--;
		if (n == 0) first = last;
		else last.next = null;
		return item;
	}
	
	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	
	private class ListIterator implements Iterator<Item> {
		// 16(overhead) + 8(inner class) = 24 bytes
		
		private Node current = first;	// 8(ref) bytes
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
	// unit testing (required)
	public static void main(String[] args) {
		/**
		 * Unit testing
		 * 
		 * Your main() method must call directly every public constructor 
		 * and method to help verify that they work as prescribed  
		 * (e.g., by printing results to standard output).
		 */
		
		Deque<Integer> deque = new Deque<Integer>();
		
		for (int i = 0; i < 10; i += 2) {
			deque.addFirst(i);
			deque.addLast(i + 1);
		}
		Iterator<Integer> it = deque.iterator();
		while (it.hasNext()) {
			StdOut.print(it.next() + " ");
		}
		StdOut.println("size: " + deque.size());
		
		for (int i = 0; i < 3; i++) {
			deque.removeLast();
			deque.removeFirst();
		}
		it = deque.iterator();
		while (it.hasNext()) {
			StdOut.print(it.next() + " ");
		}
		StdOut.println("size: " + deque.size());
		
		for (int i = 0; i < 6; i += 2) {
			deque.addLast(i);
			deque.addFirst(i + 1);
		}
		it = deque.iterator();
		while (it.hasNext()) {
			StdOut.print(it.next() + " ");
		}
		StdOut.println("size: " + deque.size());
		
		for (int i = 0; i < 10; i++) {
			deque.removeFirst();
		}
		for (int i = 0; i < 5; i++) {
			deque.addLast(i);
		}
		for (int a : deque) {
			for (int b : deque) {
				StdOut.print(a + "-" + b + " ");
			}
			StdOut.println();
		}
		
	}	
}
