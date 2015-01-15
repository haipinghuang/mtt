package com.loopfire.meitaotao;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopfire.meitaotao.common.BaseFragmentActivity;
import com.loopfire.meitaotao.function.barber.community.CommunityIndex;
import com.loopfire.meitaotao.function.barber.hairdressing.HairdressingForBarberIndex;
import com.loopfire.meitaotao.function.barber.my.PersonalForBarberIndex;
import com.loopfire.meitaotao.function.barber.setuptime.SetUpTimeIndex;

public class BarberMainActivity extends BaseFragmentActivity implements
		OnClickListener {

	private LinearLayout homeindex, sort, discuss, presonal;

	private ImageView img_homeindex, img_sort, img_discuss, img_presonal;

	private TextView text_homeindex, text_sort, text_discuss, text_presonal;

	private TextView newMessageNum;

	private HairdressingForBarberIndex Hairdressing;

	private CommunityIndex Community;

	private SetUpTimeIndex SetUpTime;

	private PersonalForBarberIndex personalIndex;

	private String SAVE_TAG = "save_tag";

	private String SAVE_ID = "save_id";

	private String currentContentFragmentTag = null;

	private int currentId;

	// private MessageGuideReceiver receiver;

	public static BarberMainActivity guideActivity;

	private static final int RECEIVE_MSG = 1;

	private int unReadTotal;

	private boolean isLoad;

	private int DestoryId = -1;

	private Resources resources;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case RECEIVE_MSG:
				int receiveNum = (Integer) msg.obj;
				updateTotalUI(receiveNum);
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.barber_activity_main);

		initView();
		guideActivity = this;

		if (savedInstanceState != null) {
			DestoryId = savedInstanceState.getInt(SAVE_ID);
			currentContentFragmentTag = savedInstanceState.getString(SAVE_TAG);
			removeAllFragment();
		} else {
			// currentId = R.id.opprotunity;
			currentId = R.id.homeindex;
			changeUI(currentId);
		}

		// // 根据标识符判断是否从notificati).getStringExtra("fromNotification");
		// if (fromNote != null && "1".on跳转
		// String fromNote = getIntent(equals(fromNote)) {
		// removeAllFragment();
		// changeUI(R.id.message);
		// } else {
		// if (savedInstanceState != null) {
		// DestoryId = savedInstanceState.getInt(SAVE_ID);
		// currentContentFragmentTag = savedInstanceState.getString(SAVE_TAG);
		// removeAllFragment();
		// } else {
		// // currentId = R.id.opprotunity;
		// currentId=R.id.message;
		// changeUI(currentId);
		// }
		//
		// }
		//
		// receiver = new MessageGuideReceiver();
		// IntentFilter filter = new IntentFilter("msg_in");
		// registerReceiver(receiver, filter);
		// unReadTotal = DataUtil.getUnReadTotal(this,
		// CApplication.getInstance().getLoginUserInfo().getUid());
		// updateTotalUI(unReadTotal);
		//
	}

	private void initView() {
		resources = getResources();
		// newMessageNum = (TextView) findViewById(R.id.message_num);
		homeindex = (LinearLayout) findViewById(R.id.homeindex);
		sort = (LinearLayout) findViewById(R.id.sort);
		discuss = (LinearLayout) findViewById(R.id.discuss);
		presonal = (LinearLayout) findViewById(R.id.presonal);
		homeindex.setOnClickListener(this);
		sort.setOnClickListener(this);
		discuss.setOnClickListener(this);
		presonal.setOnClickListener(this);

		img_homeindex = (ImageView) findViewById(R.id.img_homeindex);
		img_sort = (ImageView) findViewById(R.id.img_sort);
		img_discuss = (ImageView) findViewById(R.id.img_discuss);
		img_presonal = (ImageView) findViewById(R.id.img_presonal);

		text_homeindex = (TextView) findViewById(R.id.text_homeindex);
		text_sort = (TextView) findViewById(R.id.text_sort);
		text_discuss = (TextView) findViewById(R.id.text_discuss);
		text_presonal = (TextView) findViewById(R.id.text_presonal);
	}

	public void updateContent(int id) {

		try {
			Fragment fragment = null;
			String tag = null;
			final FragmentManager fm = getSupportFragmentManager();
			final FragmentTransaction tr = fm.beginTransaction();
			if (currentContentFragmentTag != null) {
				final Fragment currentFragment = fm
						.findFragmentByTag(currentContentFragmentTag);
				if (currentFragment != null) {
					tr.hide(currentFragment);// 将当前的Frament隐藏到后台去
				}
			}
			switch (id) {
			case R.id.homeindex:
				tag = HairdressingForBarberIndex.class.getSimpleName();
				final Fragment hairdress = fm.findFragmentByTag(tag);
				if (hairdress != null) {
					fragment = hairdress;
				} else {
					fragment = new HairdressingForBarberIndex();
				}
				Hairdressing = (HairdressingForBarberIndex) fragment;
				break;
			case R.id.sort:
				tag = CommunityIndex.class.getSimpleName();
				final Fragment community = fm.findFragmentByTag(tag);
				if (community != null) {
					fragment = community;
					Community = (CommunityIndex) fragment;
				} else {
					fragment = new CommunityIndex();
				}

				Community = (CommunityIndex) fragment;
				break;
			case R.id.discuss:
				tag = SetUpTimeIndex.class.getSimpleName();
				final Fragment setUpTime = fm.findFragmentByTag(tag);
				if (setUpTime != null) {
					fragment = setUpTime;
				} else {
					fragment = new SetUpTimeIndex();
				}
				SetUpTime = (SetUpTimeIndex) fragment;
				break;
			case R.id.presonal:
				tag = PersonalForBarberIndex.class.getSimpleName();
				final Fragment personal = fm.findFragmentByTag(tag);
				if (personal != null) {
					fragment = personal;
				} else {
					fragment = new PersonalForBarberIndex();
				}
				personalIndex = (PersonalForBarberIndex) fragment;
				break;
			default:
				tag = HairdressingForBarberIndex.class.getSimpleName();
				final Fragment hairdress1 = fm.findFragmentByTag(tag);
				if (hairdress1 != null) {
					fragment = hairdress1;
				} else {
					fragment = new HairdressingForBarberIndex();
				}
				Hairdressing = (HairdressingForBarberIndex) fragment;
				break;
			}
			if (fragment != null && fragment.isAdded()) {
				tr.show(fragment);// 显示要显示的frament
			} else {
				tr.add(R.id.fragment, fragment, tag);// 如果没添加，就添加进去并且会显示出来
			}
			tr.commitAllowingStateLoss();
			currentContentFragmentTag = tag;
			currentId = id;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Override
	// protected void onNewIntent(Intent intent) {
	// // TODO Auto-generated method stub
	// super.onNewIntent(intent);
	// String fromNote = intent.getStringExtra("fromNotification");
	// if (fromNote != null && "1".equals(fromNote)) {
	// changeUI(R.id.message);
	// }
	// }

	// @Override
	// protected void onStart() {
	// super.onStart();
	// if (!isLoad) {
	// int total = DataUtil.getUnReadTotal(MainActivity.this,
	// CApplication.getInstance().getLoginUserInfo().getUid());
	// updateTotalUI(total);
	// }
	// isLoad = false;
	// if (DestoryId != -1) {
	// changeUI(DestoryId);
	// DestoryId = -1;
	// }
	// }

	/**
	 * 根据未读消息数显示
	 */
	private void updateTotalUI(int total) {
		if (total > 0) {
			newMessageNum.setVisibility(View.VISIBLE);
			newMessageNum.setText(total + "");
			newMessageNum.invalidate();
		} else {
			newMessageNum.setVisibility(View.INVISIBLE);
		}
	}

	private void removeAllFragment() {

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction tr = fm.beginTransaction();
		Fragment Hairdressing = fm
				.findFragmentByTag(HairdressingForBarberIndex.class
						.getSimpleName());
		if (Hairdressing != null && Hairdressing.isAdded()) {
			tr.hide(Hairdressing);
			tr.remove(Hairdressing);
		}
		Fragment Community = fm.findFragmentByTag(CommunityIndex.class
				.getSimpleName());
		if (Community != null && Community.isAdded()) {
			tr.hide(Community);
			tr.remove(Community);
		}
		Fragment SetUpTime = fm.findFragmentByTag(SetUpTimeIndex.class
				.getSimpleName());
		if (SetUpTime != null && SetUpTime.isAdded()) {
			tr.hide(SetUpTime);
			tr.remove(SetUpTime);
		}
		Fragment personal = fm.findFragmentByTag(PersonalForBarberIndex.class
				.getSimpleName());
		if (personal != null && personal.isAdded()) {
			tr.hide(personal);
			tr.remove(personal);

		}
		tr.commitAllowingStateLoss();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(SAVE_ID, currentId);
		outState.putString(SAVE_TAG, currentContentFragmentTag);
		super.onSaveInstanceState(outState);
	}

	private void changeUI(int checkid) {

		switch (checkid) {
		case R.id.homeindex:
			// img_homeindex.setImageDrawable(resources.getDrawable(R.drawable.shangji_on));
			// img_sort.setImageDrawable(resources.getDrawable(R.drawable.renmai_down));
			// img_discuss.setImageDrawable(resources.getDrawable(R.drawable.xiaoxi_down));
			// img_presonal.setImageDrawable(resources.getDrawable(R.drawable.shezhi_down));
			text_homeindex.setTextColor(resources.getColor(R.color.action_on));
			text_sort.setTextColor(resources.getColor(R.color.action_down));
			text_discuss.setTextColor(resources.getColor(R.color.action_down));
			text_presonal.setTextColor(resources.getColor(R.color.action_down));
			updateContent(checkid);
			break;
		case R.id.sort:
			// img_homeindex.setImageDrawable(resources.getDrawable(R.drawable.shangji_down));
			// img_sort.setImageDrawable(resources.getDrawable(R.drawable.renmai_on));
			// img_discuss.setImageDrawable(resources.getDrawable(R.drawable.xiaoxi_down));
			// img_presonal.setImageDrawable(resources.getDrawable(R.drawable.shezhi_down));
			text_homeindex
					.setTextColor(resources.getColor(R.color.action_down));
			text_sort.setTextColor(resources.getColor(R.color.action_on));
			text_discuss.setTextColor(resources.getColor(R.color.action_down));
			text_presonal.setTextColor(resources.getColor(R.color.action_down));
			updateContent(checkid);
			break;
		case R.id.discuss:
			// img_homeindex.setImageDrawable(resources.getDrawable(R.drawable.shangji_down));
			// img_sort.setImageDrawable(resources.getDrawable(R.drawable.renmai_down));
			// img_discuss.setImageDrawable(resources.getDrawable(R.drawable.xiaoxi_on));
			// img_presonal.setImageDrawable(resources.getDrawable(R.drawable.shezhi_down));
			text_homeindex
					.setTextColor(resources.getColor(R.color.action_down));
			text_sort.setTextColor(resources.getColor(R.color.action_down));
			text_discuss.setTextColor(resources.getColor(R.color.action_on));
			text_presonal.setTextColor(resources.getColor(R.color.action_down));
			updateContent(checkid);
			break;
		case R.id.presonal:
			// img_homeindex.setImageDrawable(resources.getDrawable(R.drawable.shangji_down));
			// img_sort.setImageDrawable(resources.getDrawable(R.drawable.renmai_down));
			// img_discuss.setImageDrawable(resources.getDrawable(R.drawable.xiaoxi_down));
			// img_presonal.setImageDrawable(resources.getDrawable(R.drawable.hezhi_on));
			text_homeindex
					.setTextColor(resources.getColor(R.color.action_down));
			text_sort.setTextColor(resources.getColor(R.color.action_down));
			text_discuss.setTextColor(resources.getColor(R.color.action_down));
			text_presonal.setTextColor(resources.getColor(R.color.action_on));
			updateContent(checkid);
			break;
		}

	}

	// private class MessageGuideReceiver extends BroadcastReceiver {
	//
	// @Override
	// public void onReceive(Context context, Intent intent) {
	// if ("msg_in".equals(intent.getAction())) {
	// android.os.Message message = new android.os.Message();
	// message.obj = DataUtil.getUnReadTotal(MainActivity.this,
	// CApplication.getInstance().getLoginUserInfo().getUid());
	// message.what = RECEIVE_MSG;
	// mHandler.sendMessage(message);
	// }
	// }
	// }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// unregisterReceiver(receiver);
	}

	private long exitTime = 0;

	/**
	 * 再按一次退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		changeUI(id);
	}

}
