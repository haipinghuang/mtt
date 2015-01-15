package com.loopfire.meitaotao.common;


import java.util.List;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.SApplication;
import com.loopfire.meitaotao.util.FileUtils;
import com.loopfire.meitaotao.util.MD5Util;


/**
 * 通用Activity，所有Activity的父类
 *
 */
public class BaseActivity extends Activity implements IBaseActivity {

	public static final int ERROR = 10001;
	public static final int COOKIE_INVILD = 10002;

	static public int screenWidth = 0;
	static public int screenHeight = 0;

	public SApplication app;

	public TextView title_txt;
	public LinearLayout title_left;
	public LinearLayout title_background;
	public LinearLayout title_right;
	public Button button_left;
	public TextView button_right;
	private SharedPreferences spf;
	
	public void displayLeft() {
		title_left.setVisibility(4);
	}

	public void displayRight() {
		title_right.setVisibility(4);
	}

	public void setLeft(int id, String text) {
		button_left.setBackgroundResource(id);
		button_left.setText(text);
	}

	public void setLeft(String text) {
		button_left.setText(text);
	}

	public void setLeft(int id) {
		button_left.setBackgroundResource(id);
	}

	public void setRight(int id) {
		button_right.setBackgroundResource(id);
	}

	public void setRight(int id, String text) {
		button_right.setBackgroundResource(id);
		button_right.setText(text);
	}

	public void setRight(String text) {
		button_right.setText(text);
	}

	public void setTitle(String text) {
		title_txt.setText(text);
	}

	protected void onStart() {
		super.onStart();

	}

	public void onDestroy() {
		app.allActivity.remove(this);
		super.onDestroy();
	}
	
	
	public void save(Object obj, String file) {
		//String uid = this.app.getLoginUserInfo().getUid();
		String files = file ;//+ uid;
		files = MD5Util.MD5(files);
		FileUtils.write2cache(obj, files, FileUtils.getDataPath());
	}

	public Object get(String file) {
		Object obj = null;
		try {
			//String uid = this.app.getLoginUserInfo().getUid();//237dabing/830me
			
			String files = file; //+ uid+cateid;
			files = MD5Util.MD5(files);
			obj = FileUtils.readFromCache(files, FileUtils.getDataPath());
//			Log.i("honaf:obj", obj+"");
		} catch (Exception e) {
			obj=null;
			e.printStackTrace();
		}
		return obj;
	}
	

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		title_txt = (TextView) findViewById(R.id.tvTitle);
		title_left = (LinearLayout) findViewById(R.id.top_left_linear);
		title_right = (LinearLayout) findViewById(R.id.top_right_linear);
		button_left = (Button) findViewById(R.id.title_Left);
		button_right = (TextView) findViewById(R.id.title_right);
		title_background=(LinearLayout) findViewById(R.id.title_background);
	}

	public void initListener() // / 初始化事件
	{

	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		int flag = ((Integer) param[0]).intValue();// 获取第一个参数
		switch (flag) {
		case BaseActivity.ERROR:
//			Toast.makeText(this, "网络中断,请稍后操作！", 3000).show();
			break;
		case BaseActivity.COOKIE_INVILD:
			Toast.makeText(this, R.string.cookie_invild, 3000).show();
//			app.getLoginUserInfo().setUid("0");
//			spf = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
//			spf.edit().putBoolean("AUTO_ISCHECK", false).commit();
//			Intent intent = new Intent(this, LoginActivity.class);
//			this.startActivity(intent);
//			for(Activity activity : app.getAllActivity())
//			{
//				if(activity.getClass().getName().indexOf("Login") == -1)
//				{
//					activity.finish();
//				}
//			}
			
			break;
		}
	}

	protected void onStop() {
		super.onStop();
		if (!isAppOnForeground()) {
			// 程序进入后台			
			app.setActive(false);
			// MainService.isActive = false;

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		 if (!app.isActive) {
		 // 程序 从后台唤醒，进入前台
			 app.setActive(true);
			 /*
			 if(MainService.msgThread != null)
			 {
				 synchronized (MainService.msgThread) {
						try {
							MainService.msgThread.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			 }
			 */
		 }

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (SApplication) this.getApplicationContext();
		String classname = this.getClass().getName();
		Activity act = app.getActivityByName(classname);
		if (act == null) {
			app.allActivity.add(this);
		}
	}

	private void setscrollsize() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		/*
		 * float density = dm.density; screenWidth = (int)(dm.widthPixels *
		 * density + 0.5f); screenHeight = (int)(dm.heightPixels * density +
		 * 0.5f);
		 */
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
	}

	/**
	 * 程序是否在前台运行
	 * 
	 * @return
	 */
	public boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		// device
		String packageName = getApplicationContext().getPackageName();

		List<RunningAppProcessInfo> appProcesses = SApplication.activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
