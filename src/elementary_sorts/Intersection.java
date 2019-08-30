package elementary_sorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Intersection {
	
	public static Point[] genPointSet(int N, int lo, int hi) {
		Point[] ret = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = StdRandom.uniform(lo, hi);
			int y = StdRandom.uniform(lo, hi);
			ret[i] = new Point(x, y);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int N = 10;
		Point[] a = genPointSet(N, 1, 6);
		Point[] b = genPointSet(N, 1, 6);
		
		Shell.sort(a);
		Shell.sort(b);
		
		for (int i = 0; i < N; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
		for (int i = 0; i < N; i++) {
			StdOut.print(b[i] + " ");
		}
		StdOut.println();
		
		int i = 0;
		int j = 0;
		int count = 0;
		
		while (i < N && j < N) {
			if (a[i].equals(b[j])) {
				i++;
				j++;
				count++;
			}
			else if (a[i].compareTo(b[j]) < 0) {
				i++;
			}
			else {
				j++;
			}
		}
		
		StdOut.println("Intersection : " + count);
	}
	
}
