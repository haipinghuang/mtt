package com.loopfire.meitaotao.function.barber.setuptime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RelativeLayout;

import com.loopfire.meitaotao.BarberMainActivity;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.common.BaseFragment;

/**
 * 理发师——排期
 * 
 * @author Administrator
 * 
 */
public class SetUpTimeIndex extends BaseFragment implements OnClickListener {
	private RelativeLayout lay_setup_price;
	private RelativeLayout lay_wallet;
	private RelativeLayout lay_barber_shop;
	private RelativeLayout lay_schedule;

	private Context context;

	public SetUpTimeIndex() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.barber_setup_time_index,
				container, false);
		initView(view);
		initListener();
		return view;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
		super.initView(view);
		lay_setup_price = (RelativeLayout) view.findViewById(R.id.setup_price);
		lay_wallet = (RelativeLayout) view.findViewById(R.id.wallet);
		lay_barber_shop = (RelativeLayout) view.findViewById(R.id.barbershop);
		lay_schedule = (RelativeLayout) view.findViewById(R.id.schedule);
		setTitle("排期");
		displayLeft();
		displayRight();
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		lay_setup_price.setOnClickListener(this);
		lay_barber_shop.setOnClickListener(this);
		lay_schedule.setOnClickListener(this);
		lay_wallet.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		super.refresh(param);
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setup_price:
			startActivity(new Intent(context, SetUpPriceActivity.class));
			break;
		case R.id.wallet:
			startActivity(new Intent(context, WalletActivity.class));
			break;
		case R.id.barbershop:
			startActivity(new Intent(context, BarberShopForBarberActivity.class));
			break;
		case R.id.schedule:
			startActivity(new Intent(context, MyScheduleForBarberActivity.class));
			break;
		}

	}
}
