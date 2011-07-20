package com.stewsters.maths;

public class QuadMain
{

	public static void main(String[] args)
	{
		int M = Integer.parseInt(args[0]); // queries
		int N = Integer.parseInt(args[1]); // points
		mainbody(M, N);
		// testbody(M, N);
	}

	/*
	 * public static void testbody(int M, int N) { QuadTree<Float, String> st =
	 * new QuadTree<Float, String>();
	 * 
	 * for (int i = 0; i < N; i++) { Float x = (100.f * (float) Math.random());
	 * Float y = (100.f * (float) Math.random()); st.insert(x, y, "d" + i); }
	 * System.out.println("Done adding"); }
	 */

	public static void mainbody(int M, int N)
	{
		QuadTree<Float, String> st = new QuadTree<Float, String>();

		// insert N random points in the unit square
		for (int i = 0; i < N; i++)
		{
			Float x = (100.f * (float) Math.random());
			Float y = (100.f * (float) Math.random());
			st.insert(x, y, "P" + i);
		}
		System.out.println("Done preprocessing " + N + " points");

		// do some range searches
		long time = System.nanoTime();
		for (int i = 0; i < M; i++)
		{
			Float xmin = (100.f * (float) Math.random());
			Float ymin = (100.f * (float) Math.random());
			Float xmax = xmin + (100.f * (float) Math.random());
			Float ymax = ymin + (100.f * (float) Math.random());
			Interval<Float> intX = new Interval<Float>(xmin, xmax);
			Interval<Float> intY = new Interval<Float>(ymin, ymax);
			Interval2D<Float> rect = new Interval2D<Float>(intX, intY);
			//System.out.println(rect + " : ");
			st.query2D(rect);
		}
		System.out.println(((System.nanoTime()-time)/1000000000.0) + " Seconds" );
		
	}
}
