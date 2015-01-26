package com.loopfire.meitaotao.function.barber.my;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;
import com.loopfire.meitaotao.util.Util;

/**
 * 理发师--个人中心
 * 
 * @author Administrator
 * 
 */
public class PersonalCenterForBarberActivity extends BaseActivity {
//	private TextView tv_opus;
//	private TextView tv_goodat;
//	private TextView tv_comment;
//	private TextView tv_price;
	private ImageView iv_opus, iv_goodat, iv_comment, iv_price;
	private ViewPager viewPage;
	private List<View> pageList = new ArrayList<View>();
	private MyPageAdapter pageAdapter;
	private View viewOpus, viewGoodat, viewComment, viewPrice;
	private int curPage = 0;

	@Override
	public void initView() {
		super.initView();
		// tv_comment = (TextView) findViewById(R.id.tv_comment);
		// tv_goodat = (TextView) findViewById(R.id.tv_goodat);
		// tv_price = (TextView) findViewById(R.id.tv_price);
		// tv_opus = (TextView) findViewById(R.id.tv_opus);
		viewPage = (ViewPager) findViewById(R.id.viewPage);
		iv_opus = (ImageView) findViewById(R.id.iv_opus);
		iv_goodat = (ImageView) findViewById(R.id.iv_goodat);
		iv_comment = (ImageView) findViewById(R.id.iv_comment);
		iv_price = (ImageView) findViewById(R.id.iv_price);
	}

	@Override
	public void initListener() {
		super.initListener();
		title_right.setOnClickListener(this);
		// tv_comment.setOnClickListener(this);
		// tv_goodat.setOnClickListener(this);
		// tv_price.setOnClickListener(this);
		// tv_opus.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_personal_center);
		initView();
		initListener();

		viewOpus = LayoutInflater.from(this).inflate(
				R.layout.barber_my_personal_center_opus_page, null);
		viewGoodat = LayoutInflater.from(this).inflate(
				R.layout.barber_my_personal_center_goodat_page, null);
		viewComment = LayoutInflater.from(this).inflate(
				R.layout.barber_my_personal_center_comment_page, null);
		viewPrice = LayoutInflater.from(this).inflate(
				R.layout.barber_my_personal_center_price_page, null);
		GridView gridView = (GridView) viewOpus.findViewById(R.id.gridview);
		// 这里先没写适配器，以后做业务处理是删除此处，再写合适的适配器
		ImageView imageTemplate = (ImageView) viewOpus
				.findViewById(R.id.image_template);
		imageTemplate.setVisibility(View.VISIBLE);
		// gridView.addView(imageTemplate);
		// gridView.addView(imageTemplate);
		imageTemplate.setVisibility(View.GONE);
		// 这里先没写适配器，以后做业务处理是删除此处，再写合适的适配器
		pageList.add(viewOpus);
		pageList.add(viewGoodat);
		pageList.add(viewComment);
		pageList.add(viewPrice);
		viewPage.setAdapter(new MyPageAdapter(pageList));
		viewPage.setOnPageChangeListener(new MyOnPageChangeListener());

		setIndicator(curPage);
		setTitle(getString(R.string.my_opus));
		setRight(getString(R.string.new_add));
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

	private void setIndicator(int curPage2) {
		switch (curPage2) {
		case 0:
			iv_opus.setSelected(true);
			iv_goodat.setSelected(false);
			iv_comment.setSelected(false);
			iv_price.setSelected(false);
			break;
		case 1:
			iv_opus.setSelected(false);
			iv_goodat.setSelected(true);
			iv_comment.setSelected(false);
			iv_price.setSelected(false);
			break;
		case 2:
			iv_opus.setSelected(false);
			iv_goodat.setSelected(false);
			iv_comment.setSelected(true);
			iv_price.setSelected(false);
			break;
		case 3:
			iv_opus.setSelected(false);
			iv_goodat.setSelected(false);
			iv_comment.setSelected(false);
			iv_price.setSelected(true);
			break;
		}
		curPage = curPage2;

	}

	public void tabclick(View v) {
		Util.out("12132");
		switch (v.getId()) {
		case R.id.tv_goodat:
			viewPage.setCurrentItem(1);
			break;
		case R.id.tv_opus:
			viewPage.setCurrentItem(0);
			break;
		case R.id.tv_comment:
			viewPage.setCurrentItem(2);
			break;
		case R.id.tv_price:
			viewPage.setCurrentItem(3);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.top_right_linear:
			startActivity(new Intent(this, AddNewOpusForBarberActivity.class));
			break;
		}
	}

}
