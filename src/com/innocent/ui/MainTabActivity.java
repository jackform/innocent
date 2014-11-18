package com.innocent.ui;

import com.innocent.R;
import com.innocent.fragment.FragmentFactory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainTabActivity extends FragmentActivity {
	private RadioGroup underLineTab;
	private FragmentManager	fragmentManager;
	private Fragment oldFragment = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		fragmentManager = getSupportFragmentManager();
				
		underLineTab = (RadioGroup)findViewById(R.id.tab_view);
		underLineTab.check(R.id.friendlist_tab);
		underLineTab.setOnCheckedChangeListener(onTabSwitchListener);
		//display first tab fragment
		FragmentTransaction transaction = fragmentManager.beginTransaction(); 
		Fragment fragment = FragmentFactory.getInstanceByIndex(R.id.friendlist_tab);
		transaction.replace(R.id.content_view,fragment,String.valueOf(R.id.friend_list));
		transaction.commit();
		oldFragment = fragment;
	}
	
	
	//tab切换时的，改变content_view的内容
	private OnCheckedChangeListener onTabSwitchListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup parent, int checkedId) {
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			Fragment newFragment = fragmentManager.findFragmentByTag(String.valueOf(checkedId));
			if ( null == newFragment ) {
				newFragment = FragmentFactory.getInstanceByIndex(checkedId);
				transaction.hide(oldFragment);
				transaction.add(R.id.content_view, newFragment,String.valueOf(checkedId));
			} else {
				transaction.hide(oldFragment);
				transaction.show(newFragment);
			}
			oldFragment = newFragment;
			transaction.commit();
		}
	};
}
