package com.loopfire.meitaotao.function.barber.setuptime;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.util.Util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetUpPriceActivity extends BaseActivity {
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		title_right.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_activity_setup_price);
		initView();
		initListener();
		setTitle(getString(R.string.setup_price));
		setRight(getString(R.string.new_add));
	}

	@Override
	public void onClick(View v) {

		super.onClick(v);
		switch (v.getId()) {
		case R.id.top_right_linear:
			startActivity(new Intent(this, UpdatePriceActivity.class));
			break;
		}
	}

}
