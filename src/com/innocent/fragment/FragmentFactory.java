package com.innocent.fragment;

import com.innocent.R;

import android.support.v4.app.Fragment;

public class FragmentFactory {
	private static final int tabId[] = {R.id.friendlist_tab,R.id.tab2,R.id.tab3,R.id.tab4};
	public static final int NUM_OF_TAB = 4;
	public static Fragment getInstanceByIndex(int index)
	{
		Fragment fragment = null;
		switch(index) {
		case 0:
			fragment = new FriendListFragment();
			break;
		default:
			fragment = new NoneUseFragment();
			break;
		}
		return fragment;
	}
	
	public static int getIndexByCheckedId(int checkedId)
	{
		for(int i=0;i<4;i++) {
			if( tabId[i] == checkedId )
				return i;
		}
		return -1;
	}
}
