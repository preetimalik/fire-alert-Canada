package com.pixelbyte.newguifirealert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class InspectionActivity extends FragmentActivity {

	CollectionPagerAdapter mFloorRoomsAdapter;
	ViewPager mVpager;
	PagerTabStrip pts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inspection);
		
		mFloorRoomsAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
		mVpager = (ViewPager) findViewById(R.id.inspectionRoomsPager);
		mVpager.setAdapter(mFloorRoomsAdapter);
		
		ImageButton backNav = (ImageButton) findViewById(R.id.inspectionBackNavButton);
		backNav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		ImageButton manEnter = (ImageButton) findViewById(R.id.inspectionScreenSearchButton);
		manEnter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(getBaseContext(), EnterManuallyActivity.class), 1);
			}
		});
	}
	
	/* ROOM CLASSES */
	
	private class CollectionPagerAdapter extends FragmentStatePagerAdapter {
		public CollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		public Fragment getItem(int i) {
			Fragment frag = new FloorRoomFragment();
			return frag;
		}
		
		public int getCount() {
			return 4;
		}
		
		public CharSequence getPageTitle(int pos) {
			return "FLOOR" + (pos + 1);
		}
	}
	
	public static class FloorRoomFragment extends Fragment {
		
		List<String> Companies;
		List<String> Clients;
		Map<String, List<String>> ClientCompanies;
		ExpandableListView expListView;
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_rooms_layout, container, false);
			
			createCompanies();
			createClientCompanies();
			
			expListView = (ExpandableListView) rootView.findViewById(R.id.fragmentRoomsLayoutEquipmentToBeInspected);
			final ClientCompanyExpandableListAdapter expListAdapter = new ClientCompanyExpandableListAdapter(this.getActivity(), Companies, ClientCompanies);
			expListView.setAdapter(expListAdapter);
			expListView.setOnChildClickListener(new OnChildClickListener() {
				@Override
				public boolean onChildClick(ExpandableListView parent, View v, int parentPos, int childPos, long id) {
					// TODO Replace this Toast with a funtion that goes to the selected client's "page"
					//Toast.makeText(getBaseContext(), ((String)expListAdapter.getChild(parentPos, childPos)), Toast.LENGTH_SHORT).show();
					//startActivity(new Intent(parent.getContext(), InspectionActivity.class));
					Toast.makeText(parent.getContext(), v.toString(), Toast.LENGTH_SHORT).show();
					v.setBackgroundColor(Color.RED);
					return true;
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
			
			return rootView;
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
	
	//TODO Scanner Classes & Functions, and activity functions used
	
	private DataReceiver dataScanner = new DataReceiver();
	private String scanResult = "";
	
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
		//
	}
	
	private void registerScanner() {
		dataScanner = new DataReceiver();
		IntentFilter in = new IntentFilter();
		in.addAction("android.intent.action.CONTENT_NOTIFY");
		registerReceiver(dataScanner, in);
	}
	
	private void unregisterReceiver() {
		if(dataScanner != null) unregisterReceiver(dataScanner);
	}
	
	private class DataReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			findViewById(R.id.loadingScreen).setVisibility(View.VISIBLE);
			findViewById(R.id.inspectionLayout).setVisibility(View.GONE);
			findViewById(R.id.inspectionRoomsPager).setVisibility(View.GONE);
			if(intent.getAction().equals("android.intent.action.CONTENT_NOTIFY")) {
				scanResult = intent.getExtras().getString("CONTENT");
			}
			findViewById(R.id.loadingScreen).setVisibility(View.GONE);
			findViewById(R.id.inspectionLayout).setVisibility(View.VISIBLE);
			findViewById(R.id.inspectionRoomsPager).setVisibility(View.VISIBLE);
		}
	}
}
