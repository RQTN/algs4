package elementary_sorts;

import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	
	public static boolean hasSameEntries(Integer[] a, Integer[] b) {
		Shell.sort(a);
		Shell.sort(b);
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		Integer[] a = {1, 2, 3, 4, 5, 6, 7};
		Integer[] b = {2, 1, 5, 7, 4, 3, 6};
		Integer[] c = {1, 3, 5, 7, 9, 11, 13};
		
		StdOut.println("a & b : " + hasSameEntries(a, b));
		StdOut.println("b & c : " + hasSameEntries(b, c));
		
	}
	
}
