package com.innocent.fragment;

import com.innocent.R;

import android.support.v4.app.Fragment;

public class FragmentFactory {
	public static Fragment getInstanceByIndex(int index)
	{
		Fragment fragment = null;
		switch(index) {
		case R.id.friendlist_tab:
			fragment = new FriendListFragment();
			break;
		default:
			fragment = new NoneUseFragment();
			break;
		}
		return fragment;
	}
}
