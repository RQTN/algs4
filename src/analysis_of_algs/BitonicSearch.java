package analysis_of_algs;

import com.rqtn.algs.chapter1_1.BinarySearch;

public class BitonicSearch {

	public static int recursiveRank(int key, int[] a) {
		return recursiveRank(key, a, 0, a.length-1);
	}
	
	public static int recursiveRank(int key, int[] a, int lo, int hi) {
		
		if (lo > hi) return -1;
		int mid = lo + (hi - lo) / 2;
		if (a[mid] == key) return mid;
		
		int res1 = 0;
		int res2 = 0;
		
		if (a[mid] > a[mid+1]) {
			if (a[mid] > key) {
				res2 = BinarySearch.recursiveReverseRank(key, a, mid+1, hi);
			} else {
				return recursiveRank(key, a, lo, mid-1);
			}
			if (res2 == -1) {
				return recursiveRank(key, a, lo, mid-1);
			}
			else return res2;
		}
		else {
			// a[mid] < a[mid+1]
			if (a[mid] > key) {
				res1 = BinarySearch.recursiveRank(key, a, lo, mid-1);
			} else {
				return recursiveRank(key, a, mid+1, hi);
			}
			if (res1 == -1) {
				return recursiveRank(key, a, mid+1, hi);
			}
			else return res1;
		}
	}

	public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 29, 28, 27, 23, 22, 19, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7};
        
        for (int i = 0; i < 50; i++) {
            boolean b = recursiveRank(i, a) == -1 ? false : true;
            System.out.println(i + " is in array? " + b);
        }
        
    }
	
	
}
