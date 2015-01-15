package com.loopfire.meitaotao.function.barber.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
/**
 * 美发fragment的viewpage适配器
 * @author Administrator
 *
 */
public class MyPageAdapter extends PagerAdapter{
	private List<View> views;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	public MyPageAdapter(List<View> views) {
		super();
		this.views=views;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager)container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager)container).addView(views.get(position));
		return views.get(position);
	}
	

}
