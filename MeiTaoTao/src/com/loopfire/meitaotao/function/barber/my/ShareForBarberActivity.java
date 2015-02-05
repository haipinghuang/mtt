package com.loopfire.meitaotao.function.barber.my;

import android.os.Bundle;
import android.view.View;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--我的--分享
 * 
 * @author Administrator
 * 
 */
public class ShareForBarberActivity extends BaseActivity {

	@Override
	public void initView() {
	}

	@Override
	public void initListener() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_share);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		}
	}

}
