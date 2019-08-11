package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean[] grid;
	private boolean[] connectBottom;
	private WeightedQuickUnionUF uf;
	private final int size;
	private final int virtualTop;
	private int numberOfOpenSites;
	
	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		
		validate(n);
		
		grid = new boolean[n*n];
		// last 1 use as virtualTop
		connectBottom = new boolean[n*n+1];
		uf = new WeightedQuickUnionUF(n*n+1);
		size = n;
		virtualTop = n*n;
		numberOfOpenSites = 0;
	}
	
	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		
		validate(row, col);
		
		int center = xyTo1D(row, col);
		
		// if (row, col) is already opened
		if (grid[center]) {
			return ;
		}
		// else not opened
		grid[center] = true;
		numberOfOpenSites++;
		
		int above = xyTo1D(row-1, col);
		int below = xyTo1D(row+1, col);
		int left = xyTo1D(row, col-1);
		int right = xyTo1D(row, col+1);
		
		// if (row, col) is on the bottom, set connectBottom[] true 
		if (row == size) {
			connectBottom[center] = true;
		}
		
		// judge if exist a union operation that union a tree whose root has connectBottom[root] == true
		boolean bottomFlag = false;
		
		// check above, (row - 1, col)
		if (row > 1 && grid[above]) {
			if (connectBottom[uf.find(center)] || connectBottom[uf.find(above)]) {
				bottomFlag = true;
			}
			uf.union(center, above);
		}
		
		// check below, (row + 1, col)
		if (row < size && grid[below]) {
			if (connectBottom[uf.find(center)] || connectBottom[uf.find(below)]) {
				bottomFlag = true;
			}
			uf.union(center, below);
		} 
		
		// check left, (row, col - 1)
		if (col > 1 && grid[left]) {
			if (connectBottom[uf.find(center)] || connectBottom[uf.find(left)]) {
				bottomFlag = true;
			}
			uf.union(center, left);
		}
		// check right, (row, col + 1)
		if (col < size && grid[right]) {
			if (connectBottom[uf.find(center)] || connectBottom[uf.find(right)]) {
				bottomFlag = true;
			}
			uf.union(center, right);
		}
		
		// if row == 1, should have one more union: union with virtual top
		if (row == 1) {
			if (connectBottom[uf.find(center)] || connectBottom[uf.find(virtualTop)]) {
				bottomFlag = true;
			}
			uf.union(center, virtualTop);
		}
		
		// if bottom Flag is true, then set connectBottom[root of center] to true after all union ops
		if (bottomFlag) {
			connectBottom[uf.find(center)] = true;
		}
		
	}
	
	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		validate(row, col);
		return grid[xyTo1D(row, col)];
	}
	
	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		validate(row, col);
		return isOpen(row, col) && uf.connected(xyTo1D(row, col), virtualTop);
	}
	
	// returns the number of open sites
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
	
	// does the system percolate?
	public boolean percolates() {
		return connectBottom[uf.find(virtualTop)];
	}
	
	// mapping 2D coordinates to 1D coordinates
	private int xyTo1D(int row, int col) {
		return (row - 1)*size + (col - 1);
	}
	
	private void validate(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("ERROR: grid size n must be greater than 0");
		}
	}
	
	private void validate(int row, int col) {
		if (row < 1 || row > size || col < 1 || col > size) {
			throw new IllegalArgumentException("ERROR: given (row, col) is outside prescribed range");
		}
	}
	
	// test client (optional)
	public static void main(String[] args) {
		
	}
	
}
