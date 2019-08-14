package analysis_of_algs;

import java.util.Arrays;

import com.rqtn.algs.chapter1_1.BinarySearch;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSumFast {
	
	public static int count(int[] a) {
		Arrays.sort(a);
		int N = a.length;
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (BinarySearch.recursiveRank(-(a[i] + a[j]), a) > j) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a = new In(args[0]).readAllInts();
		Stopwatch stopwatch = new Stopwatch();
		StdOut.println(count(a));
		double time = stopwatch.elapsedTime();
		StdOut.println(time);
	}
}
