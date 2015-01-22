package com.loopfire.meitaotao.function.barber.schedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;
import com.loopfire.meitaotao.function.common.DialogCenterHintActivity;

/**
 * 理发师--我的排期
 * 
 * @author Administrator
 * 
 */
public class MyScheduleForBarberActivity extends BaseActivity {
	private TextView tv_already_schedule;
	private TextView tv_add_schedule;
	private ImageView iv_already_schedule;
	private ImageView iv_add_schedule;
	private ViewPager viewPage;
	private List<View> views = new ArrayList<View>();
	private int curPage = 0;

	@Override
	public void initView() {
		super.initView();
		tv_add_schedule = (TextView) findViewById(R.id.tv_add_schedule);
		tv_already_schedule = (TextView) findViewById(R.id.tv_already_schedule);
		iv_already_schedule = (ImageView) findViewById(R.id.iv_already_schedule);
		iv_add_schedule = (ImageView) findViewById(R.id.iv_add_schedule);
		viewPage = (ViewPager) findViewById(R.id.viewPage);
		View view1 = getLayoutInflater().inflate(
				R.layout.barber_schedule_viewpage_page1, null);
		View view2 = getLayoutInflater().inflate(
				R.layout.barber_schedule_viewpage_page2, null);
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

	@Override
	public void initListener() {
		super.initListener();
		tv_add_schedule.setOnClickListener(this);
		tv_already_schedule.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_schedule_my_schedule);
		initView();
		initListener();
		setIndicator(curPage);
		setTitle(getString(R.string.schedule));
		displayRight();
	}

	private void setIndicator(int curPage2) {
		switch (curPage2) {
		case 0:
			tv_already_schedule.setSelected(true);
			tv_add_schedule.setSelected(false);
			iv_already_schedule.setSelected(true);
			iv_add_schedule.setSelected(false);

			break;
		case 1:
			tv_already_schedule.setSelected(false);
			tv_add_schedule.setSelected(true);
			iv_already_schedule.setSelected(false);
			iv_add_schedule.setSelected(true);
			break;
		}
		curPage = curPage2;

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_sure:
			startActivity(new Intent(this, DialogCenterHintActivity.class));
			break;
		// --------下面两个是tab按钮
		case R.id.tv_already_schedule:
			viewPage.setCurrentItem(0);
			break;
		case R.id.tv_add_schedule:
			viewPage.setCurrentItem(1);
			break;
		}
	}

}
