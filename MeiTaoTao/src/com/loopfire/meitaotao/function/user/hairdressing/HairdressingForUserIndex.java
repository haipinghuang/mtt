package com.loopfire.meitaotao.function.user.hairdressing;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.function.barber.adapter.HairdressAdapter;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;
import com.loopfire.meitaotao.function.barber.hairdressing.BarberShopForBarberActivity;
import com.loopfire.meitaotao.function.common.BarberShopActivity;
import com.loopfire.meitaotao.listener.MyLocationListener;
import com.loopfire.meitaotao.util.Util;

/**
 * 用户——美发
 * 
 * @author Administrator
 * 
 */
public class HairdressingForUserIndex extends BaseFragment implements OnClickListener {
	private ViewPager vPage;
	private ImageView page0, page1;
	private ArrayList<View> views;
	@SuppressWarnings("unused")
	private MyPageAdapter pageAdapter;
	private int curPage;
	private LayoutInflater inflater;
	private List<String> hairdressNames = new ArrayList<String>();
	private List<String> hairdressImages = new ArrayList<String>();
	private Context context;
	private TextView barbershop;// 临时的，注意删除
	// lbs变量-------
	private String loc = null; // 保存定位信息
	public LocationClient mLocationClient = null;
	public BDLocationListener myLBSListener = null;

	public HairdressingForUserIndex() {
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
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.user_hairdress_index, container, false);
		initView(view);
		initListener();
		// 开启定位------------
		myLBSListener = new MyLocationListener(button_left);
		mLocationClient = new LocationClient(context.getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myLBSListener); // 注册监听函数
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开GPS
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(1000 * 60);// 设置发起定位请求的间隔时间为1分钟
		option.disableCache(false);// 禁止启用缓存定位
		option.setPriority(LocationClientOption.NetWorkFirst);// 网络定位优先
		mLocationClient.setLocOption(option);// 使用设置
		mLocationClient.start();// 开启定位SDK
		mLocationClient.requestLocation();// 开始请求位置
		// 开启定位------------
		return view;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
		super.initView(view);
		curPage = 0;
		views = new ArrayList<View>();
		vPage = (ViewPager) view.findViewById(R.id.viewPage1);
		barbershop = (TextView) view.findViewById(R.id.barbershop);

		// page0 = (ImageView) view.findViewById(R.id.page0);
		// page1 = (ImageView) view.findViewById(R.id.page1);
		// 初始化美发 文字和图片
		for (int i = 0; i < 8; i++) {
			hairdressNames.add("hairdress+" + (i + 1));
			hairdressImages.add("hairdressing_hairdressing_type" + (i + 1));
		}
		views = new ArrayList<View>();
		View gv1 = getGridChildView(1);
		views.add(gv1);
		vPage.setAdapter(new MyPageAdapter(views));
		setTitle(null);
		button_right.setText(null);
		setRightBackground(R.drawable.hairdressing_hairdressing_ico_search);
	}

	@Override
	public void initListener() {
		super.initListener();
		vPage.setOnPageChangeListener(new MyOnPageChangeListener());
		button_right.setOnClickListener(this);
		barbershop.setOnClickListener(this);
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
	public void onClick(View v) {
		Util.out(v.getId() + "");
		switch (v.getId()) {
		case R.id.title_right:
			startActivity(new Intent(context, SearchForUserActivity.class));
			break;
		case R.id.barbershop:
			startActivity(new Intent(context, BarberShopActivity.class));
			break;
		}
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
				page0.setImageDrawable(getResources().getDrawable(R.drawable.hairdressing_hairdressing_ico_circle1));
				page1.setImageDrawable(getResources().getDrawable(R.drawable.hairdressing_hairdressing_ico_circle2));
				break;
			case 1:
				page0.setImageDrawable(getResources().getDrawable(R.drawable.hairdressing_hairdressing_ico_circle2));
				page1.setImageDrawable(getResources().getDrawable(R.drawable.hairdressing_hairdressing_ico_circle1));
				break;
			}
			curPage = arg0;
		}

	}

}
