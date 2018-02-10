package com.pixelbyte.firealert;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScannerActivity extends Activity {

	private String ACTION_CONTENT_NOTIFY = "android.intent.action.CONTENT_NOTIFY";
	private DataReceiver dataScanner = new DataReceiver();
	private EditText tv_getdata_from_scanner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanner);
		
		Button confirm = (Button) findViewById(R.id.confirmScan);
		
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getIntent().putExtra("Return", "SUCCESS");
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
		getMenuInflater().inflate(R.menu.scanner, menu);
		return true;
	}

    @Override
	protected void onResume() {
    	registerScanner();
    	initialComponent();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver();
		super.onDestroy();
	}
	
	private void initialComponent() {
		tv_getdata_from_scanner = (EditText)findViewById(R.id.scanField);
		tv_getdata_from_scanner.setText(getIntent().getStringExtra("Pass"));
	}
	
	private void registerScanner() {
		dataScanner = new DataReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION_CONTENT_NOTIFY);
		registerReceiver(dataScanner, intentFilter);
	}
	
	private void unregisterReceiver() {
		if (dataScanner != null) unregisterReceiver(dataScanner);
	}
    
	private class DataReceiver extends BroadcastReceiver {
		String content = "";
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ACTION_CONTENT_NOTIFY)) {
				((TextView)findViewById(R.id.scannerStatus)).setText("Input Received.");
				Bundle bundle = new Bundle();
				bundle  = intent.getExtras();
				content = bundle.getString("CONTENT");
				tv_getdata_from_scanner.setText(content);
			}
		}
	}
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_scanner);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.scanner, menu);
//		return true;
//	}

}
