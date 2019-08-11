package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private final double CONFIDENCE_95;
	private final double mean;
	private final double stddev;
	private final double confidenceLo;
	private final double confidenceHi;
	

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		
		validate(n, trials);
		
		CONFIDENCE_95 = 1.96;
		double[] result = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			Percolation percolation = new Percolation(n);
			while (!percolation.percolates()) {
				percolation.open(StdRandom.uniform(n)+1, StdRandom.uniform(n)+1);
			}
			result[i] = (double) percolation.numberOfOpenSites() / (n*n);
		}
		
		mean = StdStats.mean(result);
		stddev = StdStats.stddev(result);
		confidenceLo = mean - CONFIDENCE_95 * stddev / Math.sqrt(trials);
		confidenceHi = mean + CONFIDENCE_95 * stddev / Math.sqrt(trials);
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return mean;
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return stddev;
	}
	
	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return confidenceLo;
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return confidenceHi;
	}
	
	private void validate(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("ERROR: grid size n and trials should all greater than 0");
		}
	}
	
	// test client 
	public static void main(String[] args) {
		
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(n, trials);
		StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
		
	}
	
}
