package com.loopfire.meitaotao.function.barber.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--我的--关于
 * 
 * @author Administrator
 * 
 */
public class AboutActivity extends BaseActivity {
	private LinearLayout lay_advance;
	private LinearLayout lay_newVersion;
	private LinearLayout lay_exit;

	@Override
	public void initView() {
		super.initView();
		lay_exit = (LinearLayout) findViewById(R.id.exit);
		lay_newVersion = (LinearLayout) findViewById(R.id.newVersion);
		lay_advance = (LinearLayout) findViewById(R.id.advance);
	}

	@Override
	public void initListener() {
		super.initListener();
		lay_exit.setOnClickListener(this);
		lay_newVersion.setOnClickListener(this);
		lay_advance.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_about);
		initView();
		initListener();
		setTitle(getString(R.string.about));
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.exit:
			startActivity(new Intent(this, ExitForBarberActivity.class));
			break;
		case R.id.advance:
			startActivity(new Intent(this, AdviceActivity.class));
			break;
		case R.id.newVersion:
			break;
		}
	}

}
