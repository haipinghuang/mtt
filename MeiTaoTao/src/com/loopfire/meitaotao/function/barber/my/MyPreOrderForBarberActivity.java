package com.loopfire.meitaotao.function.barber.my;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;

/**
 * 理发师--我的--预约单
 * 
 * @author Administrator
 * 
 */
public class MyPreOrderForBarberActivity extends BaseActivity {
	private TextView tv_expire_order;
	private TextView tv_valid_order;
	private ImageView iv_expire_order;
	private ImageView iv_valid_order;
	private ViewPager viewPage;
	private List<View> views = new ArrayList<View>();
	private int curPage = 0;

	@Override
	public void initView() {
		super.initView();
		tv_expire_order = (TextView) findViewById(R.id.tv_expire_order);
		tv_valid_order = (TextView) findViewById(R.id.tv_valid_order);
		iv_expire_order = (ImageView) findViewById(R.id.iv_expire_order);
		iv_valid_order = (ImageView) findViewById(R.id.iv_valid_order);
		viewPage = (ViewPager) findViewById(R.id.viewPage);
		View view1 = getLayoutInflater().inflate(
				R.layout.barber_my_mypre_order_viewpage_page, null);
		View view2 = getLayoutInflater().inflate(
				R.layout.barber_my_mypre_order_viewpage_page, null);
		views.add(view1);
		views.add(view2);
		viewPage.setAdapter(new MyPageAdapter(views));
		viewPage.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			setIndicator(arg0);
		}

	}

	private void setIndicator(int arg0) {
		switch (arg0) {
		case 0:
			tv_expire_order.setSelected(true);
			tv_valid_order.setSelected(false);
			iv_expire_order.setSelected(true);
			iv_valid_order.setSelected(false);

			break;
		case 1:
			tv_expire_order.setSelected(false);
			tv_valid_order.setSelected(true);
			iv_expire_order.setSelected(false);
			iv_valid_order.setSelected(true);
			break;
		}
		curPage = arg0;
	}

	@Override
	public void initListener() {
		super.initListener();
		tv_expire_order.setOnClickListener(this);
		tv_valid_order.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_mypre_order);
		initView();
		initListener();
		setIndicator(curPage);
		setRight(getString(R.string.mypre_order));
		displayRight();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_expire_order:
			viewPage.setCurrentItem(0);
			break;
		case R.id.tv_valid_order:
			viewPage.setCurrentItem(1);
			break;
		}
	}

}
