package stacks_and_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackWithMax {

	private Stack<Double> valStack;
	private Stack<Double> maxStack;
	
	public StackWithMax() {
		valStack = new Stack<Double>();
		maxStack = new Stack<Double>();
	}
	
	
	public boolean isEmpty() {
		return valStack.isEmpty();
	}
	
	public void push(Double item) {
		valStack.push(item);
		if (maxStack.isEmpty()) {
			maxStack.push(item);
		} else {
			Double max = getMax();
			if (max > item) {
				maxStack.push(max);
			} else {
				maxStack.push(item);
			}
		}
	}
	
	public Double pop() {
		if (valStack.isEmpty()) throw new NoSuchElementException();
		maxStack.pop();
		return valStack.pop();
	}
	
	public Double getMax() {
		if (maxStack.isEmpty()) throw new NoSuchElementException();
		Iterator<Double> iter = maxStack.iterator();
		Double max = iter.next();
		return max;
	}
	
	public int size() {
		return valStack.size();
	}
	
	public static void main(String[] args) {
		StackWithMax stack = new StackWithMax();
		StdOut.println("Input '+ 4' to push 4 in stack OR '-' to pop from stack");
		while (!StdIn.isEmpty()) {
			String op = StdIn.readString();
			if (op.equals("-")) stack.pop();
			else if (op.equals("+")) {
				String val = StdIn.readString();
				stack.push(Double.parseDouble(val));
			}
			
			StdOut.println("Max : " + stack.getMax());
		}
	}
	
}
