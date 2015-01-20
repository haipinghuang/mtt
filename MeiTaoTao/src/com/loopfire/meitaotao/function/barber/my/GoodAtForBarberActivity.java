package com.loopfire.meitaotao.function.barber.my;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--我的--擅长
 * 
 * @author Administrator
 * 
 */
public class GoodAtForBarberActivity extends BaseActivity {
	private GridView gridView;

	@Override
	public void initView() {
		super.initView();
		gridView=(GridView) findViewById(R.id.gridview);
	}

	@Override
	public void initListener() {
		super.initListener();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_good_at);
		initView();
		initListener();
		setTitle(getString(R.string.goodat));
		displayRight();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		}
	}

}
