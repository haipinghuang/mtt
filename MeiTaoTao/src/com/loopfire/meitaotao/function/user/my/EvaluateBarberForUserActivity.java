package com.loopfire.meitaotao.function.user.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.util.Util;

/**
 * 用户-我的-- 评价理发师
 * 
 * @author Administrator
 * 
 */
public class EvaluateBarberForUserActivity extends BaseActivity {

	@Override
	public void initView() {
		super.initView();
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_my_personal_center);
		initView();
		initListener();
		setTitle(getString(R.string.personal_center));
		displayRight();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_search:
//			startActivity(new Intent(this, SearchResultForUserActivity.class));
			break;
		}
	}
}
