package com.rqtn.algs.chapter1_1;

import edu.princeton.cs.algs4.StdOut;

public class MyMath {
	public static int gcd(int p, int q) {
		if (q == 0) return p;
		int r = p % q;
		return gcd(q, r);
	}
	
	public static boolean isPrime(int N) {
		if (N < 2) return false;
		for (int i = 2; i*i <= N; ++i) {
			if (N % i == 0) return false;
		}
		return true;
	}
	
	public static double sqrt(double c) {
		if (c < 0) return Double.NaN;
		double err = 1e-15;
		double t = c;
		while(Math.abs(t - c/t) > err * t)
			t = (c/t + t) / 2.0;
		return t;
	}
	
	public static double H(int N) {
		double sum = 0.0;
		for (int i = 1; i <= N; i++)
			sum += 1.0 / i;
		return sum;
	}
	
	public static void main(String[] args) {
		// Test gcd
		StdOut.println();
		StdOut.printf("GCD of %d and %d is %d\n", 18, 48, gcd(18, 48));
		StdOut.printf("GCD of %d and %d is %d\n", 24, 36, gcd(24, 36));
		StdOut.printf("GCD of %d and %d is %d\n", 18, 36, gcd(18, 36));
		
		// Test isPrime
		StdOut.println();
		StdOut.printf("Is %d a Prime? %b\n", 17, isPrime(17));
		StdOut.printf("Is %d a Prime? %b\n", 33, isPrime(33));
		
		// Test sqrt
		StdOut.println();
		StdOut.printf("Sqrt of %f is %f\n", 3.0, sqrt(3.0));
		StdOut.printf("Sqrt of %f is %f\n", 4.0, sqrt(4.0));
		StdOut.printf("Sqrt of %f is %f\n", -4.0, sqrt(-4.0));
		
		// Test H
		StdOut.println();
		StdOut.printf("%d order Harmonic equal %f\n", 2, H(2));
		StdOut.printf("%d order Harmonic equal %f\n", 10, H(10));
		StdOut.printf("%d order Harmonic equal %f\n", 100, H(100));
		
		
	}
}
