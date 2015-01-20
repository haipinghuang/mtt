package com.loopfire.meitaotao.function.barber.hairdressing;

import android.os.Bundle;
import android.view.View;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

/**
 * 理发师--美发--理发店
 * 
 * @author Administrator
 * 
 */
public class BarberShopForBarberActivity extends BaseActivity {

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
		setContentView(R.layout.barber_hairdress_barbershop);
		initView();
		initListener();
		setTitle(getString(R.string.barber_shop));
		displayRight();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_sure:
//			startActivity(new Intent(this, DialogCenterHintActivity.class));
			break;
		}
	}

}
