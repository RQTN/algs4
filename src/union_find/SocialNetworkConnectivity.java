package union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SocialNetworkConnectivity {

	private WeightedQuickUnionUF uf;
	private int timestamps;
	
	public SocialNetworkConnectivity(int N) {
		uf = new WeightedQuickUnionUF(N);
	}
	
	public void formFriendShip(int p, int q) {
		uf.union(p, q);
		timestamps++;
	}
	
	public boolean isAllConnected() {
		return uf.count() == 1;
	}
	
	public int getTimestamps() {
		return timestamps;
	}
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		int M = StdIn.readInt();
		
		SocialNetworkConnectivity snc = new SocialNetworkConnectivity(N);  
		
		for (int i = 0; i < M; i++) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			snc.formFriendShip(p, q);
			if (snc.isAllConnected()) {
				StdOut.println("The earliest time is : " + snc.getTimestamps());
				break;
			}
		}
	}
	
}
