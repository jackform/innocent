package com.innocent.fragment;

import com.inncocent.adapter.FriendListExpandableAdapter;
import com.innocent.R;
import com.innocent.ui.ChatActivity;
import com.innocent.ui.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class FriendListFragment extends BaseFragment {
	private FriendListExpandableAdapter friendListAdapter;
	private ExpandableListView friendListView;
	private Context mContext;
	@Override
	public boolean isUse() {
		return true;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_main, null);  
		friendListAdapter = new FriendListExpandableAdapter(mContext);
		friendListView = (ExpandableListView)view.findViewById(R.id.friend_list);
		friendListView.setAdapter(friendListAdapter);
		friendListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
					int childPosition, long id) {
				Intent intent = new Intent(mContext,ChatActivity.class);
				startActivity(intent);
				return false;
			}
		});
		return view;
	}
}
