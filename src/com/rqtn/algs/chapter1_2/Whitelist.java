package com.rqtn.algs.chapter1_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Whitelist {

	public static void main(String[] args) {
		int[] w = In.readInts(args[0]);
		StaticSetofInts set = new StaticSetofInts(w);
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (!set.contains(key)) 
				StdOut.println(key);
		}
	}
}
