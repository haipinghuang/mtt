package com.loopfire.meitaotao.function.barber.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.api.BarberAPI;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.entity.CategoryCommunityData;
import com.loopfire.meitaotao.entity.CommunityData;
import com.loopfire.meitaotao.function.barber.adapter.CommunityListViewAdapter;
import com.loopfire.meitaotao.function.barber.adapter.MyPageAdapter;
import com.loopfire.meitaotao.view.xlistview.XListView;
import com.loopfire.meitaotao.view.xlistview.XListView.IXListViewListener;

/**
 * 社区
 * 
 * @author Administrator
 * 
 */
public class CommunityIndex extends BaseFragment implements OnClickListener {
	// 社区资料的请求码
	private final int REQUEST_DATA = 201;
	// 社区视频的请求码
	private final int REQUEST_VIDEO = 202;
	private Context context;
	private ViewPager viewPage;
	// private TextView tv_text1;
	// private TextView tv_text2;
	private TextView tv_data, tv_video;// viewpage的资料视频indicator
	private ImageView iv_data, iv_video;// viewpage的资料视频indicator
	private MyPageAdapter pageAdapter;
	private List<View> pageViews = new ArrayList<View>();
	// 资料数据集合
	private List<CategoryCommunityData> dataListViews = new ArrayList<CategoryCommunityData>();
	// 资料种类数据集合
	private Map<String, CategoryCommunityData> dataList = new HashMap<String, CategoryCommunityData>();
	private List<CategoryCommunityData> videoListViews = new ArrayList<CategoryCommunityData>();
	private Map<String, CategoryCommunityData> videoList = new HashMap<String, CategoryCommunityData>();

	private List<CommunityData> communityDatas = new ArrayList<CommunityData>();
	private List<CommunityData> communityVideos = new ArrayList<CommunityData>();
	// 资料数据adapter
	private CommunityListViewAdapter dataAdapter, videoAdapter;
	// viewpage的选中页
	private int curPage = 0;
	// 当前资料的请求页
	private int curDataPage = 1;
	// 当前视频的请求页
	private int curVideoPage = 1;
	// 每页显示的条数
	private int pageRows = 5;
	// 社区的API
	private BarberAPI api;
	private String lastReflashTime1;
	private String lastReflashTime2;
	// 资料和视频listview
	private XListView dataListView, videoListView;
	private boolean firstRequestPageTwo = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = SApplication.getInstance();
		api = new BarberAPI();
		// Log.i("", "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		// Log.i("", "onCreateView");

		View view = inflater.inflate(R.layout.barber_community_index, container, false);
		initView(view);
		initListener();
		// viewPage-----------
		// 资料page
		View dataView = inflater.inflate(R.layout.barber_community_viewpage_page, null);
		// 视频page
		View videoView = inflater.inflate(R.layout.barber_community_viewpage_page, null);
		pageViews.add(dataView);
		pageViews.add(videoView);
		pageAdapter = new MyPageAdapter(pageViews);
		viewPage.setAdapter(pageAdapter);
		viewPage.setOnPageChangeListener(new MyOnPageChangeListener());
		// viewPage-----------
		dataListView = (XListView) dataView.findViewById(R.id.listView);
		videoListView = (XListView) videoView.findViewById(R.id.listView);
		initDataListView();
		initVideoListView();
		setIndicator(curPage);
		setTitle("社区");
		displayLeft();
		displayRight();
		// 默认请求第一页数据
		api.getCommunityData(curDataPage, pageRows, this, REQUEST_DATA);
		return view;
	}

	private void initVideoListView() {
		videoAdapter = new CommunityListViewAdapter(videoListViews, context);
		videoListView.setAdapter(videoAdapter);
		videoListView.setRefreshTime(new Date().toLocaleString());
		videoListView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				// 目前这里没做刷新操作，以后要补
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(1200);
							CommunityIndex.this.getActivity().runOnUiThread(new Runnable() {
								@Override
								public void run() {
									videoListView.stopRefresh();
								}
							});
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}

			@Override
			public void onLoadMore() {
				// 目前这里没做刷新操作，以后要补
			}
		});
		videoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String path = ((CommunityData) videoAdapter.getItem(position)).getPath();
				Intent intent = new Intent(context, CommunityMsgForBarberActivity.class);
				intent.putExtra("path", path);
				startActivity(intent);
			}
		});
	}

	private void initDataListView() {
		dataAdapter = new CommunityListViewAdapter(dataListViews, context);
		dataListView.setAdapter(dataAdapter);
		dataListView.setRefreshTime(new Date().toLocaleString());
		dataListView.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				// 目前这里没做刷新操作，以后要补
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(1200);
							CommunityIndex.this.getActivity().runOnUiThread(new Runnable() {
								@Override
								public void run() {
									dataListView.stopRefresh();
								}
							});
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();

			}

			@Override
			public void onLoadMore() {
				// 目前这里没做刷新操作，以后要补
			}
		});
		dataListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String path = ((CommunityData) dataAdapter.getItem(position)).getPath();
				Intent intent = new Intent(context, CommunityMsgForBarberActivity.class);
				intent.putExtra("path", path);
				startActivity(intent);
			}
		});
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		final int request_code = Integer.parseInt((param[0].toString()));
		JSONObject jobject = null;
		switch (request_code) {
		case REQUEST_DATA:
			jobject = JSONObject.parseObject(param[1].toString());
			communityDatas = jobject.parseArray(jobject.getString("rows"), CommunityData.class);
			showDataResult(communityDatas);
			break;
		case REQUEST_VIDEO:
			try {
				Log.i("request_code", "REQUEST_VIDEO");
				jobject = JSONObject.parseObject(param[1].toString());
				communityVideos = jobject.parseArray(jobject.getString("rows"), CommunityData.class);
				showVideoResult(communityVideos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void showVideoResult(List<CommunityData> communityDatas2) {
		if (communityDatas2 != null && communityDatas2.size() != 0) {
			for (CommunityData data : communityDatas2) {
				String titleTag = data.getTitleTag();
				// 判断当前资料的数据中释放包含同意titleTag的内容
				if (videoList.containsKey(titleTag)) {
					videoList.get(titleTag).addItem(data);
				} else {
					CategoryCommunityData categoryData = new CategoryCommunityData(titleTag);
					categoryData.addItem(data);
					videoList.put(titleTag, categoryData);
					/*
					 * 此处现在的处理方式： 直接清除以前的数据，然后再重新装入不同种类的数据
					 * 
					 * 以后可做内存优化：直接把新数据添加即可
					 */

				}
			}
			videoListViews.clear();
			for (String str : videoList.keySet()) {
				videoListViews.add(videoList.get(str));
			}
			Log.i("videoListViews size:", videoListViews.size() + "");
			videoAdapter.notifyDataSetChanged();
		}

	}

	/*
	 * 显示资料数据
	 */
	private void showDataResult(List<CommunityData> communityDatas3) {
		// 请求的返回数据不为空
		if (communityDatas3 != null && communityDatas3.size() != 0) {
			for (CommunityData data : communityDatas3) {
				String titleTag = data.getTitleTag();
				// 判断当前资料的数据中释放包含同意titleTag的内容
				if (dataList.containsKey(titleTag)) {
					dataList.get(titleTag).addItem(data);
				} else {
					CategoryCommunityData categoryData = new CategoryCommunityData(titleTag);
					categoryData.addItem(data);
					dataList.put(titleTag, categoryData);
					/*
					 * 此处现在的处理方式： 直接清除以前的数据，然后再重新装入不同种类的数据
					 * 
					 * 以后可做内存优化：直接把新数据添加即可
					 */
				}
			}
			dataListViews.clear();
			for (String str : dataList.keySet()) {
				dataListViews.add(dataList.get(str));
			}
			Log.i("dataListViews size:", dataListViews.size() + "");
			dataAdapter.notifyDataSetChanged();
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
			if (firstRequestPageTwo == true) {
				api.getCommunityVideo(curDataPage, pageRows, this, REQUEST_VIDEO);
				firstRequestPageTwo = false;
			}
			tv_data.setSelected(false);
			tv_video.setSelected(true);
			iv_data.setSelected(false);
			iv_video.setSelected(true);
			break;
		default:
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
			if (0 != curPage) {
				viewPage.setCurrentItem(0);
			}
			break;
		case R.id.tv_video:
			if (1 != curPage) {
				viewPage.setCurrentItem(1);
			}
			break;
		}
	}

	/*
	 * @Override public void onActivityCreated(Bundle savedInstanceState) {
	 * Log.i("", "onActivityCreated");
	 * super.onActivityCreated(savedInstanceState); }
	 * 
	 * @Override public void onAttach(Activity activity) { Log.i("",
	 * "onAttach"); super.onAttach(activity); }
	 * 
	 * @Override public void onPause() { Log.i("", "onPause"); super.onPause();
	 * }
	 * 
	 * @Override public void onStop() { Log.i("", "onStop"); super.onStop(); }
	 * 
	 * @Override public void onStart() { Log.i("", "onStart"); super.onStart();
	 * }
	 * 
	 * @Override public void onDestroyView() { Log.i("", "onDestroyView");
	 * super.onDestroyView(); }
	 * 
	 * @Override public void onSaveInstanceState(Bundle outState) { Log.i("",
	 * "onSaveInstanceState"); super.onSaveInstanceState(outState); }
	 */
}
