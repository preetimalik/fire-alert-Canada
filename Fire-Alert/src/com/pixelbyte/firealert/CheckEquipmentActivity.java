package com.pixelbyte.firealert;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;

public class CheckEquipmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_check_equipment);
		CharSequence [] checkEquipValues = new CharSequence[3];
		checkEquipValues[0] = "Thing1";
		checkEquipValues[1] = "Thing2";
		checkEquipValues[2] = "Thing3";
		@SuppressWarnings("rawtypes")
		final ArrayList mPassList = new ArrayList();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Check Equipment")
				.setMultiChoiceItems(checkEquipValues, null, new DialogInterface.OnMultiChoiceClickListener() {
					@SuppressWarnings("unchecked")
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						if(isChecked) mPassList.add(which);
						else if (mPassList.contains(which)) mPassList.remove(which);
					}
				})
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						getIntent().putExtra("passList", mPassList);
						finish();
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
		//setContentView(builder.create().getWindow().getDecorView());
		setContentView(new View(builder.show().getContext()));
		return;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_equipment, menu);
		return true;
	}

}
