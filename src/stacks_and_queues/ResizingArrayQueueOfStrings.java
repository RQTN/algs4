package stacks_and_queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueueOfStrings {

	private String[] q;
	private int head = 0;
	private int tail = 0;
	private int N = 0;
	
	public ResizingArrayQueueOfStrings() {
		q = new String[1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	private void resize(int capacity) {
		String[] copy = new String[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = q[(head + i) % q.length];
		}
		q = copy;
		head = 0;
		tail = N;
	}
	
	public void enqueue(String item) {
		if (N == q.length) resize(2 * q.length);
		q[tail] = item;
		tail = (tail + 1) % q.length;
		N++;
	}
	
	public String dequeue() {
		String item = q[head];
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
		
		ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
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
