package com.pixelbyte.newguifirealert;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ClientCompanyExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity context;
	private List<String> Company;
	private Map<String, List<String>> Clients;
	
	public ClientCompanyExpandableListAdapter(Activity context, List<String> Company, Map<String, List<String>> Clients)
	{
		this.context = context;
		this.Company = Company;
		this.Clients = Clients;
	}
	
	@Override
	public Object getChild(int parentPos, int childPos) {
		return Clients.get(Company.get(parentPos)).get(childPos);
	}

	@Override
	public long getChildId(int parentPos, int childPos) {
		return childPos;
	}

	@Override
	public View getChildView(int parentPos, int childPos, boolean isLastChild, View childView, ViewGroup parentView) {
		LayoutInflater inflater = context.getLayoutInflater();
		if(childView == null) childView = inflater.inflate(R.layout.clientcompanyexpandablelistchildlayout, null);
		
		/* Implement an onClickListner here for the TextView of the child, or the ImageView if you add one, if you want the adapter to edit something */
		
		//Put the child's name in the list
		((TextView)childView.findViewById(R.id.clientCompanyExpandableListChild)).setText((String) getChild(parentPos, childPos));
		return childView;
	}

	@Override
	public int getChildrenCount(int parentPos) {
		return Clients.get(Company.get(parentPos)).size();
	}

	@Override
	public Object getGroup(int parentPos) {
		return Company.get(parentPos);
	}

	@Override
	public int getGroupCount() {
		return Company.size();
	}

	@Override
	public long getGroupId(int parentPos) {
		return parentPos;
	}

	@Override
	public View getGroupView(int parentPos, boolean isExpanded, View thisView, ViewGroup parentView) {
		if(thisView == null) thisView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.clientcompanyexpandablelistparentlayout, null);
		((TextView)thisView.findViewById(R.id.clientCompanyExpandableListParent)).setText((String)getGroup(parentPos));
		return thisView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int parentPos, int childPos) {
		return true;
	}

}
