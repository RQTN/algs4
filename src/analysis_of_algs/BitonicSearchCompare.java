package analysis_of_algs;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class BitonicSearchCompare {
	
	public static int[] genBitonicArray(int N, int seed, double pLeft, double pRight) {
		
		StdRandom.setSeed(seed);
		
		int[] a = new int[N];
		
		int beg = 0;
		int end = a.length - 1;
		int count = 0;
		int val = 0;
		
		while (count < N) {
			val++;
			
			double p = StdRandom.uniform();
			if (p <= pLeft) {
				a[beg] = val;
				beg++;
				count++;
			} else if (pLeft < p && p <= pLeft + pRight) {
				a[end] = val;
				end--;
				count++;
			}
		}
		
		return a;
	}

	public static void main(String[] args) {

		int N = 3000000;
		int[] a = genBitonicArray(N, 1, 0.3, 0.5);
		StdOut.println("Bitonic Array Size: " + N + "\n");
		
		StdOut.print("BitonicSearch - Result of search 6666: ");
		Stopwatch timer1 = new Stopwatch();
		StdOut.println(BitonicSearch.find(a, 6666));
		StdOut.print("BitonicSearch - Time for search 1~" + N + ": ");
		for (int i = 1; i < N; i++) {
			BitonicSearch.find(a, i);
		}
		double time1 = timer1.elapsedTime();
		StdOut.println(time1 + "\n");
		
		Stopwatch timer2 = new Stopwatch();
		StdOut.print("BitonicSearchStd - Result of search 6666: ");
		StdOut.println(BitonicSearchStd.find(a, 6666));
		StdOut.print("BitonicSearchStd - Time for search 1~" + N + ": ");
		for (int i = 1; i < N; i++) {
			BitonicSearchStd.find(a, i);	
		}
		double time2 = timer2.elapsedTime();
		StdOut.println(time2 + "\n");
	
		Stopwatch timer3 = new Stopwatch();
		StdOut.print("BitonicSearchBonus - Result of search 6666: ");
		StdOut.println(BitonicSearchBonus.find(a, 6666));
		StdOut.print("BitonicSearchBonus - Time for search 1~" + N + ": ");
		for (int i = 1; i < N; i++) {
			BitonicSearchBonus.find(a, i);	
		}
		double time3 = timer3.elapsedTime();
		StdOut.println(time3);
	}
	
}
