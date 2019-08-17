package analysis_of_algs;

import edu.princeton.cs.algs4.StdDraw;

public class DrawBitonicArray {

	public static void main(String[] args) {
		int N = 500;
		int[] a = BitonicSearchCompare.genBitonicArray(N, 0, 0.2, 0.5);
		int mid = a.length / 2;
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, 1.5 * N);
		for (int i = 0; i < a.length; i++) {
			StdDraw.setPenRadius(0.01);
			if (i == mid) {
				StdDraw.setPenRadius(0.02);
				StdDraw.text(i+20, a[i], "mid");
			}
			if (i >= mid) StdDraw.setPenColor(StdDraw.RED);
			else {
				if (a[i] <= a[mid]) StdDraw.setPenColor(StdDraw.BLUE);
				else StdDraw.setPenColor(StdDraw.BLACK);
				
			}
			if (i == mid - 114) {
				StdDraw.setPenRadius(0.02);
				StdDraw.setPenColor(StdDraw.GREEN);
			}
			StdDraw.point(i, a[i]);
		}
	}
}
