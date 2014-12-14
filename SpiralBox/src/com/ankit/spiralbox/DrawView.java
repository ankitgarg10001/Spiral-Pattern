package com.ankit.spiralbox;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

import com.ankit.SpiralLogic.MatrixSpiral;

public class DrawView extends View {
	private Paint paint;
	private Context context;
	private int width;
	private final int N;
	private int spacing;
	private final ArrayList<Point> COORDINATES;
	private Canvas canvas;
	private int strokeWidth;
	private int currNumber;
	private int delay;
	private Boolean toInitializeVariables;
	private Thread AnimationThread; // to store runnable for filling numbers

	public DrawView(Context context, int width, int n) {
		super(context);
		this.context = context;
		this.width = width - 40;
		this.N = n;
		this.delay = 1000 / n;
		this.currNumber = 1;
		this.toInitializeVariables = true;
		this.AnimationThread = new Thread(runnable);
		this.COORDINATES = MatrixSpiral.generateSpiral(n); // blank final
															// concept
		this.paint = new Paint();
		paint.setAntiAlias(true);
	}

	@Override
	public void onDraw(Canvas canvas) {
		this.canvas = canvas;
		drawSquare();
		drawMesh();
		if (toInitializeVariables)
			determineMaxTextSize();// To be dynamic for cell size

		AnimationThread.run(); // animation for filling numbers by
								// initiating runnable

	}

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			insertNumber(currNumber++);// fill numbers upto next digit
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				Log.d("DrawView", e.getMessage());
			}
			if (currNumber < N * N)
				invalidate();
		}
	};

	private void insertNumber(int number) {

		int initx = (int) (spacing / 2) + 10;
		int inity = initx + spacing / 5;
		int currentlyFilling = 0;
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(Color.WHITE);
		Iterator<Point> iterator = COORDINATES.iterator();
		while (iterator.hasNext() && currentlyFilling <= number) {
			Point point = (Point) iterator.next();
			// Log.d("DrawView", point.x + "   " + point.y);
			canvas.drawText(String.valueOf(++currentlyFilling), initx + point.y
					* spacing, inity + point.x * spacing, paint);
		}
	}

	private void drawMesh() {
		if (toInitializeVariables) {
			spacing = (width - 10) / N;
			strokeWidth = spacing / 20;
			Log.d("drawView", "spacing=  " + spacing);
		}
		int myColor = context.getResources().getColor(R.color.background);
		paint.setColor(myColor);
		paint.setStrokeWidth(strokeWidth);

		for (int i = 1; i < N; i++) {
			int point = 10 + i * spacing;
			/*
			 * Log.d("drawView", "line from " + point + " " + " 10 " + point +
			 * " " + width); Log.d("drawView", "line from point 10 " + point +
			 * " " + width + " " + point);
			 */
			canvas.drawLine(point, 10, point, width, paint);
			canvas.drawLine(10, point, width, point, paint);
		}
	}

	private void drawSquare() {
		int myColor = context.getResources().getColor(R.color.mesh);
		paint.setColor(myColor);
		paint.setStrokeWidth(0);
		// Log.d("drawView", "Square from 10 to " + width);
		canvas.drawRect(10, 10, width, width, paint);
	}

	private void determineMaxTextSize() {
		int size = 10; // minimun text size for visiblity
		int requiredWidth = spacing - 20;// width with padding of 10 from edges
		String str = String.valueOf(N * N); // to get max size of number
											// possible
		do {
			paint.setTextSize(++size);
		} while (paint.measureText(str) < requiredWidth);

		if (size > 180) {
			size = 180;
		}
		if (size > 80 && size <= 180) {
			size -= 50;
		}

		paint.setTextSize(size);
		toInitializeVariables = false;
		Log.d("drawView", "Text Size " + size);

	}

}