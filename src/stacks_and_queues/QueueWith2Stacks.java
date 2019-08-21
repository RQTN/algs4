package stacks_and_queues;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueWith2Stacks<Item> {
	
	private Stack<Item> inStack;
	private Stack<Item> outStack;
	
	public QueueWith2Stacks() {
		inStack = new Stack<Item>();
		outStack = new Stack<Item>();
	}
	
	public boolean isEmpty() {
		return inStack.isEmpty() && outStack.isEmpty();
	}
	
	public void enqueue(Item item) {
		inStack.push(item);
	}
	
	public Item dequeue() {
		
		if (isEmpty()) throw new NoSuchElementException();
		
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
		
		return outStack.pop();
	}
	
	public int size() {
		return inStack.size() + outStack.size();
	}
	
	public static void main(String[] args) {
		QueueWith2Stacks<String> queue = new QueueWith2Stacks<String>();
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