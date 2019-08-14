package com.rqtn.algs.chapter1_1;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
	
	public static int iterativeRank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if 	(key < a[mid]) hi = mid - 1;
			else if (key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	public static int recursiveRank(int key, int[] a) {
		return recursiveRank(key, a, 0, a.length - 1);
	}
	
	public static int recursiveRank(int key, int[] a, int lo, int hi) {
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if (key < a[mid]) return recursiveRank(key, a, lo, mid - 1);
		else if (key > a[mid]) return recursiveRank(key, a, mid + 1, hi);
		else return mid;
	}
	
	public static int recursiveReverseRank(int key, int[] a, int lo, int hi) {
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if (key > a[mid]) return recursiveReverseRank(key, a, lo, mid - 1);
		else if (key < a[mid]) return recursiveReverseRank(key, a, mid + 1, hi);
		else return mid;
	}
	
	
	public static void main(String[] args) {
		int[] whitelist = new In(args[0]).readAllInts();
		
		Arrays.sort(whitelist);
		
		while(!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (iterativeRank(key, whitelist) == -1) 
				StdOut.println(key);
			if (recursiveRank(key, whitelist) == -1)
				StdOut.println(key);
		}
		
	}
	
}
