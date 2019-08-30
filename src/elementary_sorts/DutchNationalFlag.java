package elementary_sorts;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DutchNationalFlag {
	
	private Counter colorCalls;
	private Counter swapCalls;
	private int[] buckets;
	private int N;
	
	public DutchNationalFlag(int[] b) {
		N = b.length;
		buckets = new int[N];
		for (int i = 0; i < N; i++) {
			buckets[i] = b[i];
		}
		
		colorCalls = new Counter("color");
		swapCalls = new Counter("swap");
	}
	
	public int color(int i) {
		colorCalls.increment();
		return buckets[i];
	}
	
	public void swap(int i, int j) {
		swapCalls.increment();
		int tmp = buckets[i];
		buckets[i] = buckets[j];
		buckets[j] = tmp;
	}
	
	public void sort() {
		int lo = 0;
		int hi = N-1;
		int i = 0;
		
		while (i <= hi) {
			int color = color(i);
			if (color == 1) {	// red
				swap(lo, i);
				lo++;
				i++;
			} 
			else if (color == 3) {	// blue
				swap(i, hi);
				hi--;
			}
			else {	// white
				i++;
			}
		}
	}
	
	public String getColorCalls() {
		return colorCalls.toString();
	}
	
	public String getSwapCalls() {
		return swapCalls.toString();
	}
	
	public void showBuckets() {
		for (int i = 0; i < N; i++) {
			StdOut.print(buckets[i] + " ");
		}
		StdOut.println();
	}
	
	
	
	public static void main(String[] args) {
		
		int N = 1000;
		int[] testCase = new int[N];
		for (int i = 0; i < N; i++) {
			testCase[i] = StdRandom.uniform(1, 4);
		}
		
		StdOut.println(testCase.length);
		DutchNationalFlag dnf = new DutchNationalFlag(testCase);
		
		dnf.showBuckets();
		
		dnf.sort();
		
		dnf.showBuckets();
		StdOut.println(dnf.getColorCalls());
		StdOut.println(dnf.getSwapCalls());

	}
	
}
