package com.loopfire.meitaotao.function.barber.schedule;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

public class UpdatePriceActivity extends BaseActivity{
	private TextView tv_sure;
	@Override
	public void initView() {
		super.initView();
		tv_sure=(TextView) findViewById(R.id.tv_sure);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		tv_sure.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_schedule_update_price);
		initView();
		initListener();
		setTitle(getString(R.string.setup_price));
		displayRight();
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.tv_sure:
			break;
		}
	}
	
}
