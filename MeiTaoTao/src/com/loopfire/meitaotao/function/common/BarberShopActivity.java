package com.loopfire.meitaotao.function.common;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;

/**
 * 用户、理发师--理发店
 * 
 * @author Administrator
 * 
 */
public class BarberShopActivity extends BaseActivity {
	private ViewPager vPage;
	private List<View> pageViews = new ArrayList<View>();
	private RadioGroup radioGroup_dot;
	private MyPageAdapter pageAdapter;

	@Override
	public void initView() {
		super.initView();
		vPage = (ViewPager) findViewById(R.id.viewPage);
		radioGroup_dot = (RadioGroup) findViewById(R.id.radioGroup_dot);
	}

	@Override
	public void initListener() {
		super.initListener();
		vPage.setOnPageChangeListener(new MyOnPageChangeListener(radioGroup_dot));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hairdress_barbershop);
		initView();
		initListener();
		vPageAddView();
		((RadioButton) radioGroup_dot.getChildAt(0)).setChecked(true);
		setTitle(getString(R.string.barber_shop));
		displayRight();
	}

	private class MyOnPageChangeListener implements OnPageChangeListener {
		private RadioGroup radioGroup;

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		public MyOnPageChangeListener(RadioGroup radioGroup) {
			super();
			this.radioGroup = radioGroup;
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			((RadioButton) radioGroup.getChildAt(arg0)).setChecked(true);
		}

	}

	/**
	 * 第一个vpage
	 */
	private void vPageAddView() {
		for (int i = 0; i < 3; i++) {
			ImageView view = new ImageView(getApplicationContext());
			view.setBackgroundResource(R.drawable.pic_5);
			view.setScaleType(ScaleType.FIT_XY);
			// 这里估计不可以添加相同的view
			pageViews.add(view);
			RadioButton dot = new RadioButton(this);
			dot.setButtonDrawable(R.drawable.dot_background_selecter);
			dot.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
			dot.setPadding(0, 0, 0, 0);
			radioGroup_dot.addView(dot);
		}
		pageAdapter = new MyPageAdapter(pageViews);
		vPage.setAdapter(pageAdapter);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_sure:
			// startActivity(new Intent(this, DialogCenterHintActivity.class));
			break;
		}
	}

}
