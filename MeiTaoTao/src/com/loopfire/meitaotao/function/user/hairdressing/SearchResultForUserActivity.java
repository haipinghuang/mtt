package com.loopfire.meitaotao.function.user.hairdressing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.util.Util;

/**
 * 用户--美发搜索-结果
 * 
 * @author Administrator
 * 
 */
public class SearchResultForUserActivity extends BaseActivity {

	@Override
	public void initView() {
		super.initView();
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		title_right.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_hairdress_search_result);
		initView();
		initListener();
		setLeft(null);
		setTitle(getString(R.string.hairdressing_hint));
		setRightBackground(R.drawable.hairdressing_hairdressing_ico_search);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_right:
			startActivity(new Intent(this, SearchResultForUserActivity.class));
			break;
		}
	}
}
