package com.loopfire.meitaotao.function.user.hairdressing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.util.Util;

/**
 * 用户--美发搜索
 * 
 * @author Administrator
 * 
 */
public class SearchForUserActivity extends BaseActivity {
	private TextView tv_search;

	@Override
	public void initView() {
		super.initView();
		tv_search = (TextView) findViewById(R.id.tv_search);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		title_right.setOnClickListener(this);
		tv_search.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_hairdress_search);
		initView();
		initListener();
		setRight(null);
		setTitle(getString(R.string.hairdressing_hint));
		setRightBackground(R.drawable.hairdressing_hairdressing_ico_search);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_search:
			startActivity(new Intent(this, SearchResultForUserActivity.class));
			break;
		}
	}
}
