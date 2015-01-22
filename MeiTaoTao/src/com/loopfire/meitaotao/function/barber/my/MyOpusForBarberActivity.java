package com.loopfire.meitaotao.function.barber.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.barber.schedule.UpdatePriceActivity;

/**
 * 理发师--我的--作品
 * @author Administrator
 * 
 */
public class MyOpusForBarberActivity extends BaseActivity {
	

	@Override
	public void initView() {
		super.initView();
		
	}

	@Override
	public void initListener() {
		super.initListener();
		title_right.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_myopus);
		initView();
		initListener();
		setTitle(getString(R.string.my_opus));
		setRight(getString(R.string.new_add));
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.top_right_linear:
			startActivity(new Intent(this, AddNewOpusForBarberActivity.class));
			break;
		}
	}

}
