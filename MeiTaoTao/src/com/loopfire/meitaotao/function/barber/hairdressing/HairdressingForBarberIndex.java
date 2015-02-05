package com.loopfire.meitaotao.function.barber.hairdressing;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.api.BarberAPI;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.function.barber.adapter.HairdressAdapter;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;
import com.loopfire.meitaotao.function.common.BarberShopActivity;
import com.loopfire.meitaotao.listener.MyLocationListener;
import com.loopfire.meitaotao.util.Util;

/**
 * 理发师——美发
 * 
 * @author Administrator
 * 
 */
public class HairdressingForBarberIndex extends BaseFragment implements OnClickListener {
	private static final int REQUEST_GET_ALL_HAIRDRESS_TYPE = 101;
	private ViewPager vPage, vPage2;// 上下两个viewpage
	private List<View> pageViews1 = new ArrayList<View>();// 第一个vpage的页面
	private List<View> pageViews2 = new ArrayList<View>();
	private MyPageAdapter pageAdapter;
	private RadioGroup radioGroup1;
	private int curPage;
	private Context context;
	private LayoutInflater inflater;
	private List<String> hairdressNames = new ArrayList<String>();
	private List<String> hairdressImages = new ArrayList<String>();

	private TextView barbershop;// 临时的，注意删除
	private BarberAPI api;
	// lbs变量-------
	private String loc = null; // 保存定位信息
	public LocationClient mLocationClient = null;
	public BDLocationListener myLBSListener = null;

	// lbs变量-------
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
		View view = inflater.inflate(R.layout.barber_hairdress_index, container, false);
		this.inflater = inflater;
		initView(view);
		initListener();
		vPage.setCurrentItem(curPage);
		vPageAddView();
		((RadioButton) radioGroup1.getChildAt(curPage)).setChecked(true);
		// api.getAllHairdressType(true, HairdressingForBarberIndex.this,
		// REQUEST_GET_ALL_HAIRDRESS_TYPE);
		api.getAllHairdressType(true, HairdressingForBarberIndex.this, REQUEST_GET_ALL_HAIRDRESS_TYPE);
		// api.getAllHairdressType(true, 1, 5, this,
		// REQUEST_GET_ALL_HAIRDRESS_TYPE);

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

	/**
	 * 第一个vpage
	 */
	private void vPageAddView() {
		for (int i = 0; i < 3; i++) {
			ImageView view = new ImageView(context);
			view.setBackgroundResource(R.drawable.pic_3);
			view.setScaleType(ScaleType.FIT_XY);
			// 这里估计不可以添加相同的view
			pageViews1.add(view);
			final RadioButton dot = new RadioButton(context);
			dot.setButtonDrawable(R.drawable.dot_background_selecter);
			dot.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
			dot.setPadding(0, 0, 0, 0);
			radioGroup1.addView(dot);
		}
		pageAdapter = new MyPageAdapter(pageViews1);
		vPage.setAdapter(pageAdapter);
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stubs
		super.initView(view);
		api = new BarberAPI();
		curPage = 0;
		// views = new ArrayList<View>();
		vPage = (ViewPager) view.findViewById(R.id.viewPage1);
		vPage2 = (ViewPager) view.findViewById(R.id.viewPage2);
		barbershop = (TextView) view.findViewById(R.id.barbershop);
		radioGroup1 = (RadioGroup) view.findViewById(R.id.radioGroup_dot);
		// ----------------
		List<View> views1 = new ArrayList<View>();
		// View view0 =
		// inflater.inflate(R.layout.barber_hairdress_viewpage_page001, null);
		// View view1 =
		// inflater.inflate(R.layout.barber_hairdress_viewpage_page001, null);
		// View view2 =
		// inflater.inflate(R.layout.barber_hairdress_viewpage_page001, null);
		// views1.add(view0);
		// views1.add(view1);
		// views1.add(view2);
		// pageAdapter = new MyPageAdapter(views1);
		// vPage.setAdapter(pageAdapter);

		// 初始化美发 文字和图片
		for (int i = 0; i < 8; i++) {
			hairdressNames.add("hairdress+" + (i + 1));
			hairdressImages.add("hairdressing_hairdressing_type" + (i + 1));
		}
		List<View> views2 = new ArrayList<View>();
		View gv1 = getGridChildView(1);
		views2.add(gv1);
		vPage2.setAdapter(new MyPageAdapter(views2));
		setTitle(null);
		setTitleRight(null);
		setRightBackground(R.drawable.hairdressing_hairdressing_ico_search);
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
		vPage.setOnPageChangeListener(new MyOnPageChangeListener(radioGroup1));
		barbershop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.barbershop:
			startActivity(new Intent(context, BarberShopActivity.class));
			break;
		}
	}

	/**
	 * 停止LBS，减少资源消耗
	 */
	public void stopLBSListener() {
		if (mLocationClient != null && mLocationClient.isStarted()) {
			mLocationClient.stop();// 关闭定位SDK
			mLocationClient = null;
		}
	}

	@Override
	public void onDestroy() {
		stopLBSListener();
		super.onDestroy();
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

}
