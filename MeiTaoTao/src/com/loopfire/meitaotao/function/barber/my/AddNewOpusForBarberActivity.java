package com.loopfire.meitaotao.function.barber.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.barber.setuptime.UpdatePriceActivity;

/**
 * 理发师--我的作品--新增
 * 
 * @author Administrator
 * 
 */
public class AddNewOpusForBarberActivity extends BaseActivity {
	private TextView tv_takePhoto;
	private TextView tv_localUpload;

	@Override
	public void initView() {
		super.initView();
		tv_takePhoto = (TextView) findViewById(R.id.tv_takePhoto);
		tv_localUpload = (TextView) findViewById(R.id.tv_localUpload);
	}

	@Override
	public void initListener() {
		super.initListener();
		tv_localUpload.setOnClickListener(this);
		tv_takePhoto.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_activity_addnew_opus);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_takePhoto:
			startActivity(new Intent(this, UpdatePriceActivity.class));
			break;
		case R.id.tv_localUpload:
			break;
		}
	}

}
