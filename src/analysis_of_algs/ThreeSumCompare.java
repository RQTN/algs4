package analysis_of_algs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSumCompare {

	/**
	 * @param args: args[] are "1kints.txt", "2kints.txt", "4kints.txt", "8kints.txt"
	 */
	public static void main(String[] args) {
		
		StdOut.printf("%6s\t%17s\t%17s\t%17s", " ", "ThreeSum", "ThreeSumFast", "ThreeSumQuadratic");
		for (int i = 0; i < args.length; i++) {
			int[] a = new In(args[i]).readAllInts();
			int[] b = new In(args[i]).readAllInts();
			int[] c = new In(args[i]).readAllInts();
			
			Stopwatch timer1 = new Stopwatch();
			ThreeSum.count(a);
			double time1 = timer1.elapsedTime();
			
			Stopwatch timer2 = new Stopwatch();
			ThreeSumFast.count(b);
			double time2 = timer2.elapsedTime();
			
			Stopwatch timer3 = new Stopwatch();
			ThreeSumQuadratic.count(c);
			double time3 = timer3.elapsedTime();
			
			StdOut.printf("\n%6s\t%17.4f\t%17.4f\t%17.4f", args[i].substring(5,11), time1, time2, time3);
		}
	}

}
