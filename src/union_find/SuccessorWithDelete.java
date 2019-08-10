package union_find;

import edu.princeton.cs.algs4.StdOut;

public class SuccessorWithDelete {
	
	private FindMaxUF uf;
	private boolean[] isRemove;
	
	public SuccessorWithDelete(int N) {
		uf = new FindMaxUF(N);
		isRemove = new boolean[N];
	}
	
	public void remove(int x) {
		if (isRemove[x]) {
			return ;
		}
		isRemove[x] = true;
		if (x > 0 && isRemove[x-1]) {
			uf.union(x-1, x);
		}
		if (x < isRemove.length - 1 && isRemove[x+1]) {
			uf.union(x, x+1);
		}
	}
	
	public int find(int x) {
		
		if (!isRemove[x]) {
			return x;
		}
		else {
			int max = uf.findMax(x);
			if (max == isRemove.length - 1) {
				return -1;
			} else {
				return max + 1;
			}
		}
		
	}
	
	public static void main(String[] args) {
		SuccessorWithDelete swd = new SuccessorWithDelete(10);
		swd.remove(6);
		StdOut.println("Successor of 6 is : " + swd.find(6));
		swd.remove(5);
		StdOut.println("Successor of 5 is : " + swd.find(5));
		swd.remove(3);
		StdOut.println("Successor of 3 is : " + swd.find(3));
		swd.remove(4);
		StdOut.println("Successor of 4 is : " + swd.find(4));
		swd.remove(7);
		StdOut.println("Successor of 7 is : " + swd.find(7));
		StdOut.println("Successor of 3 is : " + swd.find(3));
		StdOut.println("Successor of 2 is : " + swd.find(2));
	}
	
}
