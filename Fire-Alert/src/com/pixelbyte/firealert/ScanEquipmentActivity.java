package com.pixelbyte.firealert;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;

public class ScanEquipmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_equipment);
		
		List<String> ToBeInspected = new ArrayList<String>();
		for(int i = 0; i < 32; i++) ToBeInspected.add("Parsed XML Entry " + Integer.toString(i+1));
		
		ArrayAdapter<String> notInspectedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ToBeInspected);
		ListView NIItems = (ListView) findViewById(R.id.listView1);
		NIItems.setAdapter(notInspectedAdapter);
		
		List<String> hasBeenInspected = new ArrayList<String>();
		for(int i = 0; i < 24; i++) hasBeenInspected.add("Parsed XML Entry " + Integer.toString(i+1));
		
		ArrayAdapter<String> hasBeenInspectedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hasBeenInspected);
		ListView HBIItems = (ListView) findViewById(R.id.listView2);
		HBIItems.setAdapter(hasBeenInspectedAdapter);
		
		Button continueButton = (Button) findViewById(R.id.ContinueButton);

		continueButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startActivity(new Intent(getBaseContext(), SaveResultsActivity.class));
			}
		});
		
		continueButton.setEnabled(false);
		
		Button scanButton = (Button) findViewById(R.id.scanButton);
		scanButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent scanInt = new Intent(getBaseContext(), ScannerActivity.class);
				scanInt.putExtra("Pass", "Test1");
				startActivityForResult(scanInt, 1);
			}
		});
		
		Button enterManuallyButton = (Button) findViewById(R.id.enterManButton);
		
		enterManuallyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(getBaseContext(), EnterManuallyActivity.class),2);
			}
		});
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		((Button) findViewById(R.id.ContinueButton)).setEnabled(false);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan_equipment, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 1 || requestCode == 2)
		{
			if(resultCode == RESULT_OK)
			{
				Intent forScan = new Intent(getBaseContext(), CheckEquipmentActivity.class);
				forScan.putExtra("Parsed", data.getStringExtra("Return"));
				//startActivityForResult(new Intent(getBaseContext(), CheckEquipmentActivity.class),3);
				startActivityForResult(forScan, 3);
				
				((Button)findViewById(R.id.ContinueButton)).setEnabled(true);
				//((TextView)findViewById(R.id.textView2)).setText(data.getStringExtra("Return"));
			}
		}
		else if(requestCode == 3)
		{
			if(resultCode == RESULT_OK)
			{
				//
			}
		}
	}
}
