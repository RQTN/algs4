package stacks_and_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> implements Iterable<Item> {
	
	private Item[] s;
	private int N = 0;
	
	public FixedCapacityStack(int capacity) {
		s = (Item[]) new Object[capacity];
	}
	
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item> {
		
		private int i = N;
		
		public boolean hasNext() {
			return i > 0; 
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return s[--i];
		}
		
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void push(Item item) {
		s[N++] = item;
	}
	
	public Item pop() {
		Item item = s[--N];
		s[N] = null;
		return item;
	}
	
	public int size() {
		return N;
	}
	
	public static void main(String[] args) {
		
		FixedCapacityStack<String> stack = new FixedCapacityStack<String>(100);
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
