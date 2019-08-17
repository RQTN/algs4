package analysis_of_algs;

import edu.princeton.cs.algs4.StdOut;

public class BitonicSearch {
	
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
	
	public static int find(int[] a, int key) {
		return find(a, key, 0, a.length-1);
	}
	
	private static int find(int[] a, int key, int lo, int hi) {
		
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if (a[mid] == key) return mid;
		
		int rbResult = 0;
		int lbResult = 0;
		
		// if a[mid] nearby is descending order
		if (a[mid] > a[mid+1]) {
			// if a[mid] > key, rightBinarySearch on a[mid+1]~a[hi]  
			if (a[mid] > key) {
				rbResult = rightBinarySearch(a, key, mid+1, hi);
				// if rightBinarySearch not find, then key also may on a[lo]~a[mid-1]
				if (rbResult == -1) {
					return find(a, key, lo, mid-1);
				} 
				else return rbResult;
			}
			// else a[mid] < key, then key only possible on a[lo]~a[mid-1]
			else {
				return find(a, key, lo, mid-1);
			}
		}
		// else a[mid] nearby is ascending order
		else {
			// if a[mid] > key, leftBinarySearch on a[lo]~a[mid-1]
			if (a[mid] > key) {
				lbResult = leftBinarySearch(a, key, lo, mid-1);
				// if leftBinarySearch not find, then key also may on a[mid+1]~a[hi]
				if (lbResult == -1) {
					return find(a, key, mid+1, hi);
				}
				else return lbResult;
			}
			// else a[mid] < key, then key only possible on a[mid+1]~a[hi]
			else {
				return find(a, key, mid+1, hi);
			}
		}
	}

	public static void main(String[] args) {
		
        int[] a = {1, 2, 3, 4, 5, 6, 29, 28, 27, 23, 22, 19, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7};
        
        for (int i = 0; i < 31; i++) {
        	int ret = find(a, i);
            StdOut.println(i + " : " + (ret == -1 ? "not find" : "find at " + ret));
        }
        
    }
	
	
}
