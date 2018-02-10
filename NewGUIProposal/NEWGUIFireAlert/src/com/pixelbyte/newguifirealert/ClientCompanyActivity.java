package com.pixelbyte.newguifirealert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ExpandableListView.OnChildClickListener;

public class ClientCompanyActivity extends Activity {

	List<String> Companies;
	List<String> Clients;
	Map<String, List<String>> ClientCompanies;
	ExpandableListView expListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_company);
		
		//TODO Parse the XML - might be able to do it in one function, ask Omar
		createCompanies();
		createClientCompanies();
		
		expListView = (ExpandableListView) findViewById(R.id.clientCompnayExpandableList);
		final ClientCompanyExpandableListAdapter expListAdapter = new ClientCompanyExpandableListAdapter(this, Companies, ClientCompanies);
		expListView.setAdapter(expListAdapter);
		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int parentPos, int childPos, long id) {
				// TODO Replace this Toast with a funtion that goes to the selected client's "page"
				//Toast.makeText(getBaseContext(), ((String)expListAdapter.getChild(parentPos, childPos)), Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getBaseContext(), InspectionActivity.class));
				return true;
			}
		});
		
		//Buttons
		ImageButton backNav = (ImageButton) findViewById(R.id.clientCompanyBackNavButton);
		backNav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			int lastPos = -1;
			@Override
			public void onGroupExpand(int pos) {
				if(lastPos != pos) expListView.collapseGroup(lastPos);
				lastPos = pos;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_company, menu);
		return true;
	}

	private void createCompanies()
	{
		//TODO Pass XML? Or retrieve here?
		Companies = new ArrayList<String>(); //remove ArrayList from imports if not necessary later
		Companies.add("Alpha Company");
		Companies.add("Another Company");
		Companies.add("Super Company");
		Companies.add("Not A Super Company");
		Companies.add("Worst Company Ever");
		Companies.add("Better-Than-The-Last-Company Company");
		Companies.add("You get the idea");
	}

	private void createClientCompanies()
	{
		//TODO Puts the clients in the companies (reverse baby-making)
		String[][] clients = new String[7][4]; //This isn't right, but it will do for now
		for(int i=0; i < 7; i++)
		{
			for(int j=0; j < 4; j++) clients[i][j] = i + "RAWR" + j;
		}
		ClientCompanies = new LinkedHashMap<String, List<String>>();
		
		for(String company : Companies)
		{
			//Checks the company name, then loads that company's clients (needs to change)
			if(company.equals(Companies.get(0))) loadChild(clients[0]);
			else if(company.equals(Companies.get(1))) loadChild(clients[1]);
			else if(company.equals(Companies.get(2))) loadChild(clients[2]);
			else if(company.equals(Companies.get(3))) loadChild(clients[3]);
			else if(company.equals(Companies.get(4))) loadChild(clients[4]);
			else if(company.equals(Companies.get(5))) loadChild(clients[5]);
			else loadChild(clients[6]);
			
			ClientCompanies.put(company, Clients);
		}
	}
	
	private void loadChild(String[] clients)
	{
		Clients = new ArrayList<String>();
		for(String client : clients) Clients.add(client);
	}
}
