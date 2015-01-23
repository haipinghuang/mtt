package com.loopfire.meitaotao.function.user.bespeak;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 用户—选择理发店
 * 
 * @author Administrator
 * 
 */
public class SelectHairdressShopForUserActivity extends BaseActivity {

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
		setContentView(R.layout.user_bespeak_select_hairdress_shop_activity);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.select_hairdress_shop:
			// startActivity(new Intent(this,
			// SearchResultForUserActivity.class));
			break;
		}
	}
}
