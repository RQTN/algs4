package stacks_and_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackWithMaxLessSpace {
	private Stack<Double> val;
	private double max;
	
	public StackWithMaxLessSpace() {
		val = new Stack<Double>();
	}
	
	public boolean isEmpty() {
		return val.isEmpty();
	}
	
	public void push(Double item) {
		if (val.isEmpty()) {
			val.push(item);
			max = item;
		} else {
			if (item <= max) val.push(item);
			else {
				val.push(2*item-max);
				max = item;
			}
		}
	}
	
	public Double pop() {
		if (val.isEmpty()) throw new NoSuchElementException();
		Double item = val.pop();
		if (item <= max) {
			return item;
		}
		else {
			Double realItem = max;
			max = 2*max-item;
			return realItem;
		}
	}
	
	public Double getMax() {
		if (val.isEmpty()) throw new NoSuchElementException();
		return max;
	}
	
	public int size() {
		return val.size();
	}

	public static void main(String[] args) {
		StackWithMaxLessSpace stack = new StackWithMaxLessSpace();
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
