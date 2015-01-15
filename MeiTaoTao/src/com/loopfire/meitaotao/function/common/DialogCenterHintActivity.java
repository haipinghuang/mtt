package com.loopfire.meitaotao.function.common;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopfire.meitaotao.BarberMainActivity;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.util.Util;

public class DialogCenterHintActivity extends BaseActivity {
	private TextView tv_sure;
	private TextView tv_sure2;
	private TextView tv_cancel;
	private LinearLayout layout;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_center_hint);
		initView();
		initListener();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		tv_sure = (TextView) findViewById(R.id.tv_sure);
		tv_sure2 = (TextView) findViewById(R.id.tv_sure2);
		tv_cancel = (TextView) findViewById(R.id.tv_cancel);
		layout = (LinearLayout) findViewById(R.id.sure_cancel);
		type = getIntent().getStringExtra("type");
		if (null != type) {
			if ("2".equals(type)) {
				tv_sure.setVisibility(View.GONE);
				layout.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		tv_sure.setOnClickListener(this);
		tv_sure2.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_sure:
			startActivity(new Intent(this, BarberMainActivity.class));
			break;
		case R.id.tv_sure2:
			this.finish();
			break;
		}
	}

}
