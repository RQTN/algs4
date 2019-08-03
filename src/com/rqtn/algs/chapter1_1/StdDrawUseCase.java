package com.rqtn.algs.chapter1_1;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class StdDrawUseCase {

	public static void visualizeFuncValue() {
		int N = 100;
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N*N);
		StdDraw.setPenRadius(0.01);
		for (int i = 1; i <= N; i++) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.point(i, i);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.point(i, i*i);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.point(i, i*Math.log(i));
		}
	}
	
	public static void visualizeRandomArray() {
		int N = 50;
		double[] a = new double[N];
		for (int i = 0; i < N; i++)
			a[i] = StdRandom.random();
		for (int i = 0; i < N; i++) {
			double x = 1.0*i/N;
			double y = a[i]/2.0;
			double rw = 0.5/N;
			double rh = a[i]/2.0;
			StdDraw.filledRectangle(x, y, rw, rh);
		}
	}
	
	
	public static void visualizeSortedArray() {
		int N = 50;
		double[] a = new double[N];
		for (int i = 0; i < N; i++)
			a[i] = StdRandom.random();
		Arrays.sort(a);
		for (int i = 0; i < N; i++) {
			double x = 1.0*i/N;
			double y = a[i]/2.0;
			double rw = 0.5/N;
			double rh = a[i]/2.0;
			StdDraw.filledRectangle(x, y, rw, rh);
		}
	}
	
	public static void drawRightTriangle() {
		StdDraw.square(0.5, 0.5, 0.5);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.line(0.5, 0.5, 0.9, 0.5);
		StdDraw.line(0.9, 0.5, 0.5, 0.8);
		StdDraw.line(0.5, 0.8, 0.5, 0.5);
		StdDraw.circle(0.7, 0.65, 0.25);
	}
	
	
	public static void main(String[] args) {
		
		// visualizeFuncValue();
		// visualizeRandomArray();
		// visualizeSortedArray();
		drawRightTriangle();
	}
	
}
