package com.loopfire.meitaotao.function.barber.community;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.function.barber.adapter.ListViewAdapter;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;

/**
 * 社区
 * 
 * @author Administrator
 * 
 */
public class CommunityIndex extends BaseFragment implements OnClickListener {
	private Context context;
	private ViewPager viewPage;
	// private ViewPager viewPage1;
	// private ViewPager viewPage2;
	// private TextView tv_text1;
	// private TextView tv_text2;
	private TextView tv_data;
	private TextView tv_video;
	private ImageView iv_data;
	private ImageView iv_video;
	private MyPageAdapter pageAdapter;
	private ListViewAdapter listAdapter;
	private List<View> pageViews = new ArrayList<View>();
	private List<View> listViews = new ArrayList<View>();
	private int curPage = 0;

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
		View view = inflater.inflate(R.layout.barber_community_index,
				container, false);
		initView(view);
		initListener();
		View view1 = inflater.inflate(R.layout.barber_community_viewpage_page,
				null);
		View view2 = inflater.inflate(R.layout.barber_community_viewpage_page,
				null);
		pageViews.add(view1);
		pageViews.add(view2);
		pageAdapter = new MyPageAdapter(pageViews);
		viewPage.setAdapter(pageAdapter);
		viewPage.setOnPageChangeListener(new MyOnPageChangeListener());
		ListView listView1 = (ListView) view1.findViewById(R.id.listView1);
		View listView1_item = inflater.inflate(
				R.layout.barber_community_viewpage_page_list_item, null);
		View listView1_item2 = inflater.inflate(
				R.layout.barber_community_viewpage_page_list_item, null);
		listViews.add(listView1_item);
		listViews.add(listView1_item2);
		listAdapter = new ListViewAdapter(listViews);
		listView1.setAdapter(listAdapter);
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(context,
						CommunityMsgForBarberActivity.class));
			}
		});
		setIndicator(curPage);
		setTitle("社区");
		displayLeft();
		displayRight();
		return view;
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
			tv_data.setSelected(true);
			tv_video.setSelected(false);
			iv_data.setSelected(true);
			iv_video.setSelected(false);

			break;
		case 1:
			tv_data.setSelected(false);
			tv_video.setSelected(true);
			iv_data.setSelected(false);
			iv_video.setSelected(true);
			break;
		}
		curPage = arg0;
	}

	@Override
	public void initView(View view) {
		super.initView(view);
		tv_data = (TextView) view.findViewById(R.id.tv_data);
		tv_video = (TextView) view.findViewById(R.id.tv_video);
		iv_data = (ImageView) view.findViewById(R.id.iv_data);
		iv_video = (ImageView) view.findViewById(R.id.iv_video);
		viewPage = (ViewPager) view.findViewById(R.id.viewPage);
	}

	@Override
	public void initListener() {
		super.initListener();
		tv_data.setOnClickListener(this);
		tv_video.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_data:
			viewPage.setCurrentItem(0);
			break;
		case R.id.tv_video:
			viewPage.setCurrentItem(1);
			break;
		}
	}
}
