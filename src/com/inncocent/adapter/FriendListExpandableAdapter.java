package com.inncocent.adapter;
import com.innocent.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class FriendListExpandableAdapter extends  BaseExpandableListAdapter {
	private Context mContext;
	private String[] armTypes = new String []{"aaa","bbb","ccc"};
	private String[][] arms = new String[][] {{"a1","a2"},{"b1","b2"},{"c1","c2"}};
	
	public FriendListExpandableAdapter(Context context) {
		mContext = context;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return arms[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		LayoutInflater inflate = LayoutInflater.from(mContext);
		convertView = inflate.inflate(R.layout.item_friendlist_child, null);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return arms[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return armTypes[groupPosition];
	}

	@Override
	public int getGroupCount() {
		return armTypes.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		LayoutInflater inflate = LayoutInflater.from(mContext);
		convertView = inflate.inflate(R.layout.item_friendlist_group, null);
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
