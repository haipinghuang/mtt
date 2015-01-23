package com.loopfire.meitaotao.function.user.bespeak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 用户—预约流程
 * 
 * @author Administrator
 * 
 */
public class BespeakProcedureForUserActivity extends BaseActivity {
	private RelativeLayout lay_hairdress_type;
	private RelativeLayout lay_select_hairdress_shop;
	private RelativeLayout lay_bespeak_time;

	@Override
	public void initView() {
		super.initView();
		lay_hairdress_type = (RelativeLayout) findViewById(R.id.hairdress_type);
		lay_select_hairdress_shop = (RelativeLayout) findViewById(R.id.select_hairdress_shop);
		lay_bespeak_time = (RelativeLayout) findViewById(R.id.bespeak_time);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		lay_bespeak_time.setOnClickListener(this);
		lay_select_hairdress_shop.setOnClickListener(this);
		lay_hairdress_type.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_bespeak_procedure);
		initView();
		initListener();
		setTitle(getString(R.string.bespeak_hint));
		displayRight();
		displayLeft();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.select_hairdress_shop:
			startActivity(new Intent(this,
					SelectHairdressShopForUserActivity.class));
			break;
		case R.id.hairdress_type:
			startActivity(new Intent(this, HairdressTypeForUserActivity.class));
			break;
		case R.id.bespeak_time:
			startActivity(new Intent(this, BespeakTimeForUserActivity.class));
			break;
		}
	}
}
