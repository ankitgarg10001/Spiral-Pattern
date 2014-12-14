package com.ankit.spiralbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ankit.spiralbox.R.id;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button submit = (Button) findViewById(id.main_button1);
		submit.setOnClickListener(new OnClickListener() { // on Submit button
															// click

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// get textbox instance for user entered value
				EditText value = (EditText) findViewById(id.main_editText1);
				intent.putExtra("n", value.getText().toString());
				intent.setClass(getApplicationContext(), Pattern.class);
				startActivity(intent); // open new Activity for pattern view

			}
		});
	}
}
