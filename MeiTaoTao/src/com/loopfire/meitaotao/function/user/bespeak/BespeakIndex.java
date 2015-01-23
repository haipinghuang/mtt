package com.loopfire.meitaotao.function.user.bespeak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.function.user.hairdressing.SearchResultForUserActivity;

/**
 * 用户—预约
 * 
 * @author Administrator
 * 
 */
public class BespeakIndex extends BaseFragment implements OnClickListener {
	private TextView tv_bespeak;
	private Context context;

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
		View view = inflater.inflate(R.layout.user_hairdress_search_result,
				container, false);
		initView(view);
		initListener();
		return view;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
		super.initView(view);
		tv_bespeak = (TextView) view.findViewById(R.id.bespeak);
		setTitle(null);
		displayLeft();
		displayRight();
	}

	@Override
	public void initListener() {
		super.initListener();
		tv_bespeak.setOnClickListener(this);
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
		case R.id.bespeak:
			startActivity(new Intent(context,
					BespeakProcedureForUserActivity.class));
			break;
		}

	}

}
