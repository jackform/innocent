package com.innocent.ui;

import com.innocent.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private TextView mtvRegister = null;
	private Button   mbtnLogin   = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//注册
		mtvRegister = (TextView)findViewById(R.id.register);
		mtvRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
		//登录
		mbtnLogin = (Button)findViewById(R.id.btn_login);
		mbtnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		
	}
}
