package com.ankit.SpiralLogic;

import java.util.ArrayList;

import android.graphics.Point;

public class MatrixSpiral {

	// public static void main(String args[]) {
	// System.out.println(generateSpiral(5));
	// }

	public static ArrayList<Point> generateSpiral(int n) {
		ArrayList<Point> coordinates = new ArrayList<Point>();

		int minrow = 0;
		int mincol = 0;
		int maxrow = n - 1;
		int maxcol = n - 1;
		// int curnum = 0;
		while ((minrow <= maxrow || mincol <= maxcol)) {

			for (int i = mincol; i <= maxcol; i++) {
				coordinates.add(new Point(minrow, i));
				// System.out.println("( " + minrow + ", " + i + " ) : "
				// + ++curnum);
			}
			++minrow;
			for (int i = minrow; i <= maxrow; i++) {
				coordinates.add(new Point(i, maxcol));
				// System.out.println("( " + i + ", " + (maxcol) + " ) : "
				// + ++curnum);
			}
			--maxcol;
			for (int i = maxcol; i >= mincol; i--) {
				coordinates.add(new Point(maxrow, i));
				// System.out.println("( " + (maxrow) + ", " + i + " ) : "
				// + ++curnum);
			}
			--maxrow;
			for (int i = maxrow; i >= minrow; i--) {
				coordinates.add(new Point(i, mincol));
				// System.out.println("( " + i + ", " + (mincol) + " ) : "
				// + ++curnum);
			}
			++mincol;
		}
		return coordinates;
	}
}
