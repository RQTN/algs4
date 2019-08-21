package stacks_and_queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfStrings {
	
	private Node first = null;
	private int N = 0;
	
	private class Node {
		String item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void push(String item) {
		N++;
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}
	
	public String pop() {
		N--;
		String item = first.item;
		first = first.next;
		return item;
	}
	
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		LinkedStackOfStrings stack = new LinkedStackOfStrings();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-")) {
				StdOut.print(stack.pop() + " ");
			}
			else {
				stack.push(s);
			}
		}
		StdOut.println("\n" + stack.size() + " item" + (stack.size() == 1 ? "" : "s") + " left in stack");
	}
	
}
