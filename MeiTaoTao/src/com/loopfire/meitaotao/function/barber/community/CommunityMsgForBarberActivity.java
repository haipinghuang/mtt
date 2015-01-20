package com.loopfire.meitaotao.function.barber.community;

import android.os.Bundle;
import android.view.View;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--社区——详细信息
 * 
 * @author Administrator
 * 
 */
public class CommunityMsgForBarberActivity extends BaseActivity {

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
		setContentView(R.layout.barber_community_msg_detail_);
		initView();
		initListener();
		setTitle("最新最热理发样式");
		displayRight();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		}
	}

}
