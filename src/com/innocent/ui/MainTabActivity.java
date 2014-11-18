package com.innocent.ui;

import com.innocent.R;
import com.innocent.fragment.FragmentFactory;

import android.hardware.Camera.Face;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainTabActivity extends FragmentActivity {
	private RadioGroup underLineTab;
	private FragmentManager	fragmentManager;
	private Fragment[] fragments = new Fragment[FragmentFactory.NUM_OF_TAB];
	private Fragment oldFragment = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		fragmentManager = getSupportFragmentManager();
				
		underLineTab = (RadioGroup)findViewById(R.id.tab_view);
		underLineTab.check(R.id.friendlist_tab);
		underLineTab.setOnCheckedChangeListener(onTabSwitchListener);
		
		FragmentTransaction transaction = fragmentManager.beginTransaction(); 
		fragments[0] =  FragmentFactory.getInstanceByIndex(0);
		transaction.replace(R.id.content_view,fragments[0]);
		transaction.commit();
		oldFragment = fragments[0];
		Log.v("Fragment","show first fragment");
	}
	
	
	//tab切换时的，改变content_view的内容
	private OnCheckedChangeListener onTabSwitchListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup parent, int checkedId) {
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			int index = FragmentFactory.getIndexByCheckedId(checkedId);
			if( -1 == index )
				return ;
			if( null == fragments[index] ) {
				fragments[index] = FragmentFactory.getInstanceByIndex(index);
				transaction.hide(oldFragment);
				transaction.add(R.id.content_view,fragments[index]);
			} else {
				transaction.hide(oldFragment);
				transaction.show(fragments[index]);
			}
			oldFragment = fragments[index];
			transaction.commit();
		}
	};
}
