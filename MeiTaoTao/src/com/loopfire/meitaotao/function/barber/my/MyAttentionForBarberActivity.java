package com.loopfire.meitaotao.function.barber.my;

import android.os.Bundle;
import android.view.View;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--我的--关注
 * 
 * @author Administrator
 * 
 */
public class MyAttentionForBarberActivity extends BaseActivity {

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
		setContentView(R.layout.barber_my_attention);
		initView();
		initListener();
		setTitle(getString(R.string.my_attention));
		displayRight();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		}
	}

}
