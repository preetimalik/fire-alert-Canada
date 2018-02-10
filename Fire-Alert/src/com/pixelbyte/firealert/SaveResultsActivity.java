package com.pixelbyte.firealert;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SaveResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_results);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_results, menu);
		return true;
	}

}
