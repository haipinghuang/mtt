package com.loopfire.meitaotao.function.barber.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.common.BaseFragment;

/**
 * 理发师——我的
 * 
 * @author Administrator
 * 
 */
public class PersonalForBarberIndex extends BaseFragment implements
		OnClickListener {
	private RelativeLayout lay_order;
	private RelativeLayout lay_goodAt;
	private RelativeLayout lay_level;
	private RelativeLayout lay_phoneNo;
	private RelativeLayout lay_share;
	private RelativeLayout lay_kefu;
	private RelativeLayout lay_about;
	private LinearLayout btn_opus;
	private LinearLayout btn_attention;
	private LinearLayout btn_fans;
	private LinearLayout btn_message;
	private ImageView iv_head;
	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
	}

	@Override
	public void initView(View view) {
		lay_order = (RelativeLayout) view.findViewById(R.id.order);
		lay_goodAt = (RelativeLayout) view.findViewById(R.id.goodAt);
		lay_level = (RelativeLayout) view.findViewById(R.id.level);
		lay_phoneNo = (RelativeLayout) view.findViewById(R.id.phoneNo);
		lay_share = (RelativeLayout) view.findViewById(R.id.share);
		lay_kefu = (RelativeLayout) view.findViewById(R.id.kefu);
		lay_about = (RelativeLayout) view.findViewById(R.id.about);

		btn_attention = (LinearLayout) view.findViewById(R.id.btn_attention);
		btn_message = (LinearLayout) view.findViewById(R.id.btn_message);
		btn_fans = (LinearLayout) view.findViewById(R.id.btn_fans);
		btn_opus = (LinearLayout) view.findViewById(R.id.btn_opus);
		iv_head = (ImageView) view.findViewById(R.id.iv_head);
	}

	@Override
	public void initListener() {
		super.initListener();
		btn_opus.setOnClickListener(this);
		btn_fans.setOnClickListener(this);
		btn_message.setOnClickListener(this);
		btn_attention.setOnClickListener(this);

		lay_about.setOnClickListener(this);
		lay_kefu.setOnClickListener(this);
		lay_share.setOnClickListener(this);
		lay_phoneNo.setOnClickListener(this);
		lay_level.setOnClickListener(this);
		lay_goodAt.setOnClickListener(this);
		lay_order.setOnClickListener(this);
		iv_head.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about:
			startActivity(new Intent(context, AboutActivity.class));
			break;
		case R.id.order:
			startActivity(new Intent(context, MyPreOrderForBarberActivity.class));
			break;
		case R.id.goodAt:
			startActivity(new Intent(context, GoodAtForBarberActivity.class));
			break;
		case R.id.level:
			break;
		case R.id.kefu:
			break;
		case R.id.share:
			startActivity(new Intent(context, ShareForBarberActivity.class));
			break;

		case R.id.phoneNo:
			startActivity(new Intent(context, MyPhoneForBarberActivity.class));
			break;
		case R.id.iv_head:
			startActivity(new Intent(context, PersonalCenterForBarberActivity.class));
			break;
		// -------------
		case R.id.btn_attention:
			startActivity(new Intent(context,
					MyAttentionForBarberActivity.class));
			break;
		case R.id.btn_message:
			startActivity(new Intent(context, MyMsgForBarberActivity.class));
			break;
		case R.id.btn_fans:
			startActivity(new Intent(context, MyFansForBarberActivity.class));
			break;
		case R.id.btn_opus:
			startActivity(new Intent(context, MyOpusForBarberActivity.class));
			break;

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.barber_my_index, container,
				false);
		initView(view);
		initListener();
		return view;
	}

	public PersonalForBarberIndex() {
		super();
	}
}
