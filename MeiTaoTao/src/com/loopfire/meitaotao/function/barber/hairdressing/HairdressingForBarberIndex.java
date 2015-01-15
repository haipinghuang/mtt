package com.loopfire.meitaotao.function.barber.hairdressing;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.function.barber.adapter.HairdressAdapter;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;

/**
 * 理发师——美发
 * 
 * @author Administrator
 * 
 */
public class HairdressingForBarberIndex extends BaseFragment implements
		OnClickListener {
	private ViewPager vPage, vPage2;
	private ImageView page0, page1, page2;
	private ArrayList<View> views;
	private MyPageAdapter pageAdapter;
	private int curPage;
	private Context context;
	private LayoutInflater inflater;
	private List<String> hairdressNames = new ArrayList<String>();
	private List<String> hairdressImages = new ArrayList<String>();

	public HairdressingForBarberIndex() {
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
		View view = inflater.inflate(R.layout.barber_hairdress_index,
				container, false);
		this.inflater = inflater;
		initView(view);
		initListener();
		return view;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
		super.initView(view);
		curPage = 0;
		views = new ArrayList<View>();
		vPage = (ViewPager) view.findViewById(R.id.viewPage1);
		vPage2 = (ViewPager) view.findViewById(R.id.viewPage2);

		page0 = (ImageView) view.findViewById(R.id.page0);
		page1 = (ImageView) view.findViewById(R.id.page1);
		page2 = (ImageView) view.findViewById(R.id.page2);

		List<View> views1 = new ArrayList<View>();
		View view0 = inflater.inflate(R.layout.barber_viewpage_page, null);
		View view1 = inflater.inflate(R.layout.barber_viewpage_page, null);
		View view2 = inflater.inflate(R.layout.barber_viewpage_page, null);
		views1.add(view0);
		views1.add(view1);
		views1.add(view2);
		pageAdapter = new MyPageAdapter(views1);
		vPage.setAdapter(pageAdapter);

		// 初始化美发 文字和图片
		for (int i = 0; i < 8; i++) {
			hairdressNames.add("hairdress+" + (i + 1));
			hairdressImages.add("hairdressing_hairdressing_type" + (i + 1));
		}
		List<View> views2 = new ArrayList<View>();
		View gv1 = getGridChildView(1);
		views2.add(gv1);
		vPage2.setAdapter(new MyPageAdapter(views2));
		setTitle("美发");
		displayLeft();
		displayRight();
	}

	private View getGridChildView(int i) {
		View view = View.inflate(context, R.layout.gridview_hairdress, null);
		GridView gridview = (GridView) view.findViewById(R.id.gridview);
		List<String> names = null, images = null;
		if (i == 1) {
			names = hairdressNames.subList(0, 8);
			images = hairdressImages.subList(0, 8);
		}
		gridview.setAdapter(new HairdressAdapter(names, images, context));
		return view;
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
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		vPage.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	@Override
	public void onClick(View v) {

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
			switch (arg0) {
			case 0:
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle1));
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle2));
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle2));
				break;
			case 1:
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle2));
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle1));
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle2));
				break;
			case 2:
				page0.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle2));
				page1.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle2));
				page2.setImageDrawable(getResources().getDrawable(
						R.drawable.hairdressing_hairdressing_ico_circle1));
				break;
			}
			curPage = arg0;
		}

	}

}
