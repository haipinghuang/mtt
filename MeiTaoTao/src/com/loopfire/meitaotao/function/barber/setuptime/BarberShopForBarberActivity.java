package com.loopfire.meitaotao.function.barber.setuptime;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.common.DialogCenterHintActivity;

/**
 * 理发师--可赴约理发店
 * 
 * @author Administrator
 * 
 */
public class BarberShopForBarberActivity extends BaseActivity {
	private ListView lv_shopName;
	private ListView lv_shopDetail;
	private TextView tv_shopName_item;
	private TextView tv_sure;
	private LinearLayout lay_shop_detail_item;
	
	private List<View> shopNames=new ArrayList<View>();

	@Override
	public void initView() {
		super.initView();
		lv_shopDetail = (ListView) findViewById(R.id.lv_shopDetail);
		lv_shopName = (ListView) findViewById(R.id.lv_shopName);
		tv_shopName_item = (TextView) findViewById(R.id.shop_name_item);
		tv_sure = (TextView) findViewById(R.id.tv_sure);
		lay_shop_detail_item = (LinearLayout) findViewById(R.id.shop_detail_item);
	}

	@Override
	public void initListener() {
		super.initListener();
		tv_sure.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_activity_barber_shop);
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
			startActivity(new Intent(this, DialogCenterHintActivity.class));
			break;
		}
	}

}
