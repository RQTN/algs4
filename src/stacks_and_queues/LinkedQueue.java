package stacks_and_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedQueue<Item> implements Iterable<Item> {
	
	private Node first, last;
	private int N = 0;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		
		private Node current = first;
		
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
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(Item item) {
		N++;
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
	}
	
	public Item dequeue() {
		N--;
		Item item = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		return item;
	}
	
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		
		LinkedQueue<String> queue = new LinkedQueue<String>();
		while (!StdIn.isEmpty()) {
			
			String s = StdIn.readString();
			if (s.equals("-")) {
				queue.dequeue();
			}
			else {
				queue.enqueue(s);
			}
			
			StdOut.printf("%4s | Queue Display: ", s);
			for (String str : queue) {
				StdOut.print(str + " ");
			}
			StdOut.println();
		}
		StdOut.println("\n" + queue.size() + " item" + (queue.size() == 1 ? "" : "s") + " left in queue");
	}
	
}
