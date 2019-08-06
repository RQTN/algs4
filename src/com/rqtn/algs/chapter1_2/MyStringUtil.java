package com.rqtn.algs.chapter1_2;

import edu.princeton.cs.algs4.StdOut;

public class MyStringUtil {
	
	public static boolean isPalindrome(String s) {
		int N = s.length();
		for (int i = 0; i < N/2; i++)
			if (s.charAt(i) != s.charAt(N-i-1))
				return false;
		return true;
	}
	
	public static boolean isSorted(String[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i-1].compareTo(a[i]) > 0)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// test isPalindrome
		StdOut.println("Is abcba Palindrome? : " + isPalindrome("abcba"));
		StdOut.println("Is abcab Palindrome? : " + isPalindrome("abcab"));
		
		// test isSorted
		String[] a = {"abbb", "bccc", "bddd", "cfff"};
		StdOut.print("Is {" + a[0]);
		for (int i = 1; i < a.length; ++i) {
			StdOut.print(", " + a[i]);
		}
		StdOut.print("} Sorted? : " + isSorted(a));
		
		StdOut.println();
		
		String[] b = {"abbb", "bddd", "baaa", "accc"};
		StdOut.print("Is {" + b[0]);
		for (int i = 1; i < b.length; i++) {
			StdOut.print(", " + b[i]);
		}
		StdOut.print("} Sorted? : " + isSorted(b));
	}
	
	
}
