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
import android.widget.Spinner;

public class CompanyFormsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_company_forms);
		
		List<String> ClientCompanySpinner = new ArrayList<String>();
		ClientCompanySpinner.add("AlphaCompany");
		ClientCompanySpinner.add("CompanyTheSecond");
		
		ArrayAdapter<String> CCadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ClientCompanySpinner);
		CCadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner CCItems = (Spinner) findViewById(R.id.spinner1);
		CCItems.setAdapter(CCadapter);
		
		
		//This needs to be changed to read from separate lists depending on the previous entry
		List<String> ClientLocationSpinner = new ArrayList<String>();
		ClientLocationSpinner.add("BaconShack");
		ClientLocationSpinner.add("VeganGathering");
		
		ArrayAdapter<String> CLadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ClientLocationSpinner);
		CLadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner CLItems = (Spinner) findViewById(R.id.spinner2);
		CLItems.setAdapter(CLadapter);
		
		Button nextButton = (Button) findViewById(R.id.button1);

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startActivity(new Intent(getBaseContext(), ScanEquipmentActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.company_forms, menu);
		return true;
	}

}
