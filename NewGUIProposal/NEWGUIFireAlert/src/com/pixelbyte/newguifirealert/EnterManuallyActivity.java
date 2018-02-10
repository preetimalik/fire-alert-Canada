package com.pixelbyte.newguifirealert;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

public class EnterManuallyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_manually);
		
		Button confirm = (Button) findViewById(R.id.confirmManEnter);
		
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getIntent().putExtra("ManEnterResult", ((EditText) findViewById(R.id.manEnter)).getText().toString());
				setResult(RESULT_OK, getIntent());
				finish();
			}
		});
		
		Button cancel = (Button) findViewById(R.id.cancelManEnter);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED, getIntent());
				finish();
			}
		});
	}

}
