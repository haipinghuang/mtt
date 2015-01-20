package com.loopfire.meitaotao.function.barber.my;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
/**
 * 理发师--我的--分反馈意见
 * @author Administrator
 *
 */
public class AdviceActivity extends BaseActivity {

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
		setContentView(R.layout.barber_my_advice);
		initView();
		initListener();
		setTitle(getString(R.string.advance));
		setRight(getString(R.string.commit));
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.title_right:
			break;
		}
	}

}
