package com.loopfire.meitaotao.function.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.register.RegisterActivity;

public class LoginActivity extends BaseActivity {
	private TextView tv_login;
	private TextView tv_go_register;
	private TextView tv_forget_pwd;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		tv_login = (TextView) findViewById(R.id.tv_login);
		tv_go_register = (TextView) findViewById(R.id.tv_go_register);
		tv_forget_pwd = (TextView) findViewById(R.id.tv_forget_pwd);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		tv_login.setOnClickListener(this);
		tv_go_register.setOnClickListener(this);
		tv_forget_pwd.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {

		case R.id.tv_go_register:
			startActivity(new Intent(this, RegisterActivity.class));
			break;
		case R.id.tv_forget_pwd:

			break;

		default:
			// 发送登录数据

			break;
		}
	}
}
