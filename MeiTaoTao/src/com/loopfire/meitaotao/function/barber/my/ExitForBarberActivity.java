package com.loopfire.meitaotao.function.barber.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--我的--退出
 * 
 * @author Administrator
 * 
 */
public class ExitForBarberActivity extends BaseActivity {
	private LinearLayout close;
	private LinearLayout exit_login;

	@Override
	public void initView() {
		super.initView();
		close = (LinearLayout) findViewById(R.id.close);
		exit_login = (LinearLayout) findViewById(R.id.exit_login);
	}

	@Override
	public void initListener() {
		super.initListener();
		close.setOnClickListener(this);
		exit_login.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_exit);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.close:
			barberExit();
			break;
		case R.id.exit_login:
			barberExit();
			break;
		}
	}

	private void barberExit() {
		System.exit(0);
	}

}
