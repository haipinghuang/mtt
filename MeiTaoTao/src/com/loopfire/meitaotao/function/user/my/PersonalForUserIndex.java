package com.loopfire.meitaotao.function.user.my;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.function.barber.my.AboutActivity;
import com.loopfire.meitaotao.function.barber.my.MyAttentionForBarberActivity;
import com.loopfire.meitaotao.function.barber.my.MyFansForBarberActivity;
import com.loopfire.meitaotao.function.barber.my.MyMsgForBarberActivity;
import com.loopfire.meitaotao.function.barber.my.MyPhoneForBarberActivity;
import com.loopfire.meitaotao.function.barber.my.MyPreOrderForBarberActivity;
import com.loopfire.meitaotao.function.barber.my.PersonalCenterForBarberActivity;
import com.loopfire.meitaotao.function.barber.my.ShareForBarberActivity;
import com.loopfire.meitaotao.function.register.ServeProtocolActivity;

/**
 * 用户——我的
 * 
 * @author Administrator
 * 
 */
public class PersonalForUserIndex extends BaseFragment implements OnClickListener {
	private RelativeLayout lay_order;
	private RelativeLayout lay_ask_for_be_barber;
	private RelativeLayout lay_exit;
	private RelativeLayout lay_phoneNo;
	private RelativeLayout lay_share;
	private RelativeLayout lay_kefu;
	private RelativeLayout lay_about;
	private LinearLayout btn_attention;
	private LinearLayout btn_show_myself;
	private LinearLayout btn_message;
	private ImageView iv_head;
	private Context context;

	public PersonalForUserIndex() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.user_my_index, container, false);
		initView(view);
		initListener();
		return view;
	}

	@Override
	public void initView(View view) {
		super.initView(view);
		lay_order = (RelativeLayout) view.findViewById(R.id.order);
		lay_ask_for_be_barber = (RelativeLayout) view.findViewById(R.id.ask_for_be_barber);
		lay_exit = (RelativeLayout) view.findViewById(R.id.exit);
		lay_phoneNo = (RelativeLayout) view.findViewById(R.id.phoneNo);
		lay_share = (RelativeLayout) view.findViewById(R.id.share);
		lay_kefu = (RelativeLayout) view.findViewById(R.id.kefu);
		lay_about = (RelativeLayout) view.findViewById(R.id.about);

		btn_attention = (LinearLayout) view.findViewById(R.id.btn_attention);
		btn_message = (LinearLayout) view.findViewById(R.id.btn_message);
		btn_show_myself = (LinearLayout) view.findViewById(R.id.btn_show_myself);
		iv_head = (ImageView) view.findViewById(R.id.iv_head);
	}

	@Override
	public void initListener() {
		super.initListener();
		btn_show_myself.setOnClickListener(this);
		btn_message.setOnClickListener(this);
		btn_attention.setOnClickListener(this);

		lay_about.setOnClickListener(this);
		lay_kefu.setOnClickListener(this);
		lay_share.setOnClickListener(this);
		lay_phoneNo.setOnClickListener(this);
		lay_exit.setOnClickListener(this);
		lay_ask_for_be_barber.setOnClickListener(this);
		lay_order.setOnClickListener(this);
		iv_head.setOnClickListener(this);
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
		case R.id.about:
			startActivity(new Intent(context, AboutActivity.class));
			break;
		case R.id.order:
			startActivity(new Intent(context, MyPreOrderForBarberActivity.class));
			break;
		case R.id.ask_for_be_barber:
			startActivity(new Intent(context, ServeProtocolActivity.class));
			break;
		case R.id.kefu:
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:15879949335"));
			startActivity(intent);
			break;
		case R.id.exit:
			app.finishAll();
			break;
		case R.id.share:
			startActivity(new Intent(context, ShareForBarberActivity.class));
			break;
		case R.id.phoneNo:
			startActivity(new Intent(context, MyPhoneForBarberActivity.class));
			break;
		case R.id.iv_head:
			startActivity(new Intent(context, PersonalCenterForUserActivity.class));
			break;
		// -------------
		case R.id.btn_attention:
			startActivity(new Intent(context, MyAttentionForBarberActivity.class));
			break;
		case R.id.btn_message:
			startActivity(new Intent(context, MyMsgForBarberActivity.class));
			break;
		case R.id.btn_show_myself:
			startActivity(new Intent(context, MyFansForBarberActivity.class));
			break;

		}

	}

}
