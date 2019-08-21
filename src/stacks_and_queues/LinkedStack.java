package stacks_and_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStack<Item> implements Iterable<Item> {
	
	private Node first = null;
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
	
	public void push(Item item) {
		N++;
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}
	
	public Item pop() {
		N--;
		Item item = first.item;
		first = first.next;
		return item;
	}
	
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		LinkedStack<String> stack = new LinkedStack<String>();
		while (!StdIn.isEmpty()) {
			
			String s = StdIn.readString();
			if (s.equals("-")) {
				stack.pop();
			}
			else {
				stack.push(s);
			}
			
			StdOut.printf("%4s | Stack Display: ", s);
			for (String str : stack) {
				StdOut.print(str + " ");
			}
			StdOut.println();
		}
		StdOut.println("\n" + stack.size() + " item" + (stack.size() == 1 ? "" : "s") + " left in stack");
		
	}
	
}
