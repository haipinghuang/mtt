package com.loopfire.meitaotao.function.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

public class EntryPwdActivity extends BaseActivity{
	private EditText et_password;
	private Button bt_next;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_pwd);
		initView();
		initListener();
	}
	@Override
	public void initListener() {
		super.initListener();
		bt_next.setOnClickListener(this);
	}
	@Override
	public void initView() {
		super.initView();
		bt_next=(Button) findViewById(R.id.signup_btn_next);
		et_password=(EditText) findViewById(R.id.et_password);
	}
	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.signup_btn_next:
			startActivity(new Intent(this, UploadIDcardActivity.class));
			break;
		}
		
	}
}
