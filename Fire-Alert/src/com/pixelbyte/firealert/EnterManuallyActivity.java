package com.pixelbyte.firealert;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterManuallyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_manually);
		
		Button confirm = (Button) findViewById(R.id.confirmScan);
		
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getIntent().putExtra("Return", ((EditText) findViewById(R.id.scanField)).getText().toString());
				setResult(RESULT_OK, getIntent());
				finish();
			}
			
		});
		
		Button cancel = (Button) findViewById(R.id.cancelScan);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED, getIntent());
				finish();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_manually, menu);
		return true;
	}

}
