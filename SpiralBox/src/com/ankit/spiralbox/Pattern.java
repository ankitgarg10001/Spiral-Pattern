package com.ankit.spiralbox;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ankit.spiralbox.R.id;

public class Pattern extends Activity {

	Intent intent; // gets value from previous activity
	int n; // stores user entered "n"
	LinearLayout layout; // to draw square and fill pattern
	TextView value;
	DrawView drawView; // Class to draw matrix and fill numbers

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pattern);
		intent = getIntent();

		layout = (LinearLayout) findViewById(id.pattern_linearLayout);
		value = (TextView) findViewById(id.pattern_textView1);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		try {
			n = Integer.parseInt(intent.getStringExtra("n"));
		} catch (NumberFormatException e) {
			Log.d("Pattern", e.getMessage());
			Toast.makeText(getApplicationContext(),
					"Invalid Input, Changing input to 5", Toast.LENGTH_SHORT)
					.show();
			n = 5; // set n=5 by default
		}
		if (n < 1) {
			n = 1;
			Toast.makeText(getApplicationContext(),
					"Value out of bounds, Changing input to 1",
					Toast.LENGTH_SHORT).show();
		}
		if (n > 25) {
			n = 25;
			Toast.makeText(getApplicationContext(),
					"Value out of bounds, Changing input to 25",
					Toast.LENGTH_SHORT).show();
		}
		value.setText("For 'N' = " + n);

		int width;
		int height;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			// for API version 13 and above
			Point size = new Point();
			getWindowManager().getDefaultDisplay().getSize(size);

			width = size.x;
			height = size.y;
		} else {
			// for previous android versions
			Display d = getWindowManager().getDefaultDisplay();
			width = d.getWidth();
			height = d.getHeight();
		}

		Log.d("pattern", width + " " + height);
		drawView = new DrawView(this, width, n);
		layout.addView(drawView); // add this view to layout
	}
}
