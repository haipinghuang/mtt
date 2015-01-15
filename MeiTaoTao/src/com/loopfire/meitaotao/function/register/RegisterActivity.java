package com.loopfire.meitaotao.function.register;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

public class RegisterActivity extends BaseActivity{
	private TextView tv_user;
	private TextView tv_barber;
	private TextView tv_identifyCode;
	private Button bt_next;
	private int currentUser=0; //表示点击的是用户，否则理发师

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		tv_user=(TextView) findViewById(R.id.tv_user);
		tv_barber=(TextView)findViewById(R.id.tv_barber);
		bt_next=(Button) findViewById(R.id.signup_btn_next);
		tv_identifyCode=(TextView) findViewById(R.id.tv_gain_identifyCode);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		tv_barber.setOnClickListener(this);
		tv_user.setOnClickListener(this);
		bt_next.setOnClickListener(this);
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
		setContentView(R.layout.activity_register);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_user:
			if(currentUser!=0){
				tv_user.setBackgroundResource(R.drawable.signup_signup_btn_bg_pressed_left);
				tv_user.setTextColor(getResources().getColor(R.color.white));
				tv_barber.setBackgroundDrawable(null);
				tv_barber.setTextColor(getResources().getColor(R.color.text_grey));
				currentUser=0;
			}
			break;
		case R.id.tv_barber:
			if(currentUser!=1){
				tv_barber.setBackgroundResource(R.drawable.signup_signup_btn_bg_pressed_right);
				tv_barber.setTextColor(getResources().getColor(R.color.white));
				tv_user.setBackgroundDrawable(null);
				tv_user.setTextColor(getResources().getColor(R.color.text_grey));
				currentUser=1;
			}
			break;
		case R.id.signup_btn_next:
			startActivity(new Intent(this, EntryPwdActivity.class));
			break;
		}
		
	}
	
}
