package com.loopfire.meitaotao.function.user.bespeak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.common.DialogCenterHintActivity;

/**
 * 用户—选择支付方式
 * 
 * @author Administrator
 * 
 */
public class SelectPayWayForUserActivity extends BaseActivity {
	private TextView tv_nowPay;
	private TextView tv_afterPay;

	@Override
	public void initView() {
		super.initView();
		tv_nowPay = (TextView) findViewById(R.id.tv_nowPay);
		tv_afterPay = (TextView) findViewById(R.id.tv_afterPay);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		tv_nowPay.setOnClickListener(this);
		tv_afterPay.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_bespeak_select_pay_way);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_nowPay:
			startActivity(new Intent(this, DialogCenterHintActivity.class));
			break;
		case R.id.tv_afterPay:
			startActivity(new Intent(this, DialogCenterHintActivity.class));
			break;
		}
	}
}
