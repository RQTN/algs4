package elementary_sorts;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ElementarySortCompare {
	
	/**
	 * use 1Mints.txt as input file
	 * @param args
	 */
	public static void main(String[] args) {
		StdOut.printf("%7s\t%17s\t%17s\t%17s", " ", "Selection", "Insertion", "Shell(3x+1)");
		
		int[] tmp = new In(args[0]).readAllInts();
		int[] N = { 5000, 10000, 20000, 40000, 80000 };
		
		for (int i = 0; i < N.length; i++) {
			
			Integer[] a = new Integer[N[i]];
			Integer[] b = new Integer[N[i]];
			Integer[] c = new Integer[N[i]];
			for (int j = 0; j < N[i]; j++) {
				a[j] = tmp[j];
				b[j] = tmp[j];
				c[j] = tmp[j];
			}
			
			Stopwatch timer1 = new Stopwatch();
			Selection.sort(a);
			double time1 = timer1.elapsedTime();
			
			Stopwatch timer2 = new Stopwatch();
			Insertion.sort(b);
			double time2 = timer2.elapsedTime();
			
			Stopwatch timer3 = new Stopwatch();
			Shell.sort(c);
			double time3 = timer3.elapsedTime();
			
			
			
			StdOut.printf("\n%7s\t%17.4f\t%17.4f\t%17.4f", N[i], time1, time2, time3);
		}
	
	}
	
}
