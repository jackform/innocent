package com.innocent.ui;

import com.inncocent.adapter.FriendListExpandableAdapter;
import com.innocent.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	private FriendListExpandableAdapter friendListAdapter;
	private ExpandableListView friendListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		friendListAdapter = new FriendListExpandableAdapter(MainActivity.this);
		friendListView = (ExpandableListView)findViewById(R.id.friend_list);
		friendListView.setAdapter(friendListAdapter);
		friendListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
					int childPosition, long id) {
				Intent intent = new Intent(MainActivity.this,ChatActivity.class);
				startActivity(intent);
				return false;
			}
		});
	}
}
