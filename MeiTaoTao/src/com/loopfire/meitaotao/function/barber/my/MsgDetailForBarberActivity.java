package com.loopfire.meitaotao.function.barber.my;

import android.os.Bundle;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

public class MsgDetailForBarberActivity extends BaseActivity {

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_msg_detail);
		initView();
		initListener();
		setTitle(getString(R.string.message));
		displayLeft();
	}

}
