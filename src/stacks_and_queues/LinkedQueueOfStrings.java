package stacks_and_queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedQueueOfStrings {
	
	private Node first, last;
	private int N = 0;
	
	private class Node {
		String item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(String item) {
		N++;
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
	}
	
	public String dequeue() {
		N--;
		String item = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		return item;
	}
	
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		
		LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-")) {
				StdOut.print(queue.dequeue() + " ");
			}
			else {
				queue.enqueue(s);
			}
		}
		StdOut.println("\n" + queue.size() + " item" + (queue.size() == 1 ? "" : "s") + " left in queue");
	}
	
}
