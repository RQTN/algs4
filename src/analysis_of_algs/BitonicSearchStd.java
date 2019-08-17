package analysis_of_algs;

import edu.princeton.cs.algs4.StdOut;

public class BitonicSearchStd {
	
	private static int leftBinarySearch(int[] a, int key, int lo, int hi) {
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2 ;
			if (key < a[mid]) hi = mid - 1;
			else if (key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	private static int rightBinarySearch(int[] a, int key, int lo, int hi) {
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key > a[mid]) hi = mid - 1;
			else if (key < a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	private static int findMaxIndex(int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (a[mid] > a[mid+1]) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		return hi;
	}
	
	public static int find(int[] a, int key) {
		int lo = 0;
		int hi = a.length - 1;
		int maxIndex = findMaxIndex(a);
		
		if (key == a[maxIndex]) return maxIndex;
		
		int lbResult = leftBinarySearch(a, key, lo, maxIndex - 1);
		if (lbResult != -1) return lbResult;
		
		int rbResult = rightBinarySearch(a, key, maxIndex + 1, hi);
		if (rbResult != -1) return rbResult;
		
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
