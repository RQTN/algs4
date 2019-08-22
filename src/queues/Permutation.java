package queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 
 * Performance requirements.  
 * 
 * The running time of Permutation must be linear in the size of the input. 
 * You may use only a constant amount of memory plus either one Deque or 
 * RandomizedQueue object of maximum size at most n. 
 * 
 * (For an extra challenge and a small amount of extra credit, use only 
 * one Deque or RandomizedQueue object of maximum size at most k.)
 *
 */

public class Permutation {
	/**
	 * Command-line argument.  
	 * 
	 * You may assume that 0 ≤ k ≤ n, where n is the number of string on 
	 * standard input. Note that you are not given n.
	 */
	public static void main(String[] args) {
		
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		
		if (k == 0) return;
		
		
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String s = StdIn.readString();
			if (i < k) rq.enqueue(s);
			else {
				int r = StdRandom.uniform(i + 1);
				if (r < k) {
					rq.dequeue();
					rq.enqueue(s);
				}
			}
		}
		while (!rq.isEmpty()) {
			StdOut.println(rq.dequeue());
		}
	}
}
