package analysis_of_algs;

import edu.princeton.cs.algs4.StdOut;

public class BitonicSearchBonus {

	private static int leftSearch(int[] a, int key, int lo, int hi) {
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (key <= a[mid]) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		if (key == a[lo]) return lo;
		else return -1;
	}
	
	private static int rightSearch(int[] a, int key, int lo, int hi) {
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (key >= a[mid]) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		if (key == a[lo]) return lo;
		else return -1;
	}
	
	public static int find(int[] a, int key) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (key == a[mid]) return mid;
			if (key < a[mid]) {
				int lbResult = leftSearch(a, key, lo, mid);
				if (lbResult != -1) {
					return lbResult;
				} 
				else return rightSearch(a, key, mid, hi);
			}
			else {
				if (a[mid] < a[mid+1]) {
					lo = mid + 1;
				} 
				else {
					hi = mid - 1;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
        int[] a = {1, 2, 3, 4, 5, 6, 29, 28, 27, 23, 22, 19, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7};
        
        for (int i = 0; i < 31; i++) {
        	int ret = find(a, i);
            StdOut.println(i + " : " + (ret == -1 ? "not find" : "find at " + ret));
        }
        
    }
}
