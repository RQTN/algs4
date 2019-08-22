package stacks_and_queues;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
	
	private Item[] q;
	private int head = 0;
	private int tail = 0;
	private int N = 0;
	
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item> {
		
		private int i = head;
		private int count = N;
		
		public boolean hasNext() {
			return count > 0;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			count--;
			return q[(i++) % q.length];
		}
		
	}
	
	public ResizingArrayQueue() {
		q = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = q[(head + i) % q.length];
		}
		q = copy;
		head = 0;
		tail = N;
	}
	
	public void enqueue(Item item) {
		if (N == q.length) resize(2 * q.length);
		q[tail] = item;
		tail = (tail + 1) % q.length;
		N++;
	}
	
	public Item dequeue() {
		Item item = q[head];
		q[head] = null;
		head = (head + 1) % q.length;
		N--;
		if (N > 0 && N == q.length/4) resize(q.length/2);
		return item;
	}
	
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		
		ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
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
