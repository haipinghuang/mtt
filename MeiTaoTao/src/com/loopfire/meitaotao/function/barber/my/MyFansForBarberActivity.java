package com.loopfire.meitaotao.function.barber.my;

import android.os.Bundle;
import android.view.View;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--我的--粉丝
 * @author Administrator
 * 
 */
public class MyFansForBarberActivity extends BaseActivity {

	@Override
	public void initView() {
		super.initView();
	}

	@Override
	public void initListener() {
		super.initListener();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_myfans);
		initView();
		initListener();
		setTitle(getString(R.string.my_fans));
		displayRight();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		}
	}

}
