package union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FindMaxUF {
	
	private int[] id;
	private int[] sz;
	private int[] max;
	private int count;
	
	public FindMaxUF(int N) {
		StdOut.println(this.getClass());
		count = N;
		id = new int[N];
		sz = new int[N];
		max = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
			max[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}
	
	public int findMax(int p) {
		return max[find(p)];
	}
	
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) {
			return ;
		}
		if (sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
			if (max[pRoot] > max[qRoot]) {
				max[qRoot] = max[pRoot];
			}
		}
		else {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
			if (max[qRoot] > max[pRoot]) {
				max[pRoot] = max[qRoot];
			}
		}
		count--;
	}
	
	public static void main(String[] args) {
		
		int N = StdIn.readInt();
		int M = StdIn.readInt();
		
		FindMaxUF uf = new FindMaxUF(N);
		
		for (int i = 0; i < M; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q)) {
				continue;
			}
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		
		StdOut.println();
		
		for (int i = 0; i < N; i++) {
			StdOut.print(uf.findMax(i) + " ");
		}
		
	}
	
}
