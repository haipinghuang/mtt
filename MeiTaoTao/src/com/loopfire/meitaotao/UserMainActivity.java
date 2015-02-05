package com.loopfire.meitaotao;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
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
import com.loopfire.meitaotao.function.user.bespeak.BespeakIndex;
import com.loopfire.meitaotao.function.user.hairdressing.HairdressingForUserIndex;
import com.loopfire.meitaotao.function.user.my.PersonalForUserIndex;

public class UserMainActivity extends BaseFragmentActivity implements OnClickListener {

	private LinearLayout homeindex, bespeak, presonal;

	private ImageView img_homeindex, img_bespeak, img_presonal;
	private TextView text_homeindex, text_bespeak, text_presonal;

	private HairdressingForUserIndex Hairdressing;

	private BespeakIndex Bespeak;

	private PersonalForUserIndex personalIndex;

	private String SAVE_TAG = "save_tag";

	private String SAVE_ID = "save_id";

	private String currentContentFragmentTag = null;

	private int currentId;

	// private MessageGuideReceiver receiver;

	public static UserMainActivity guideActivity;

	private static final int RECEIVE_MSG = 1;

	private int unReadTotal;

	private boolean isLoad;

	private int DestoryId = -1;

	private Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_activity_main);

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

		// // 根据标识符判断是否从notification跳转
		// String fromNote = getIntent().getStringExtra("fromNotification");
		// if (fromNote != null && "1".equals(fromNote)) {
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
		bespeak = (LinearLayout) findViewById(R.id.bespeak);
		presonal = (LinearLayout) findViewById(R.id.presonal);
		homeindex.setOnClickListener(this);
		bespeak.setOnClickListener(this);
		presonal.setOnClickListener(this);

		img_homeindex = (ImageView) findViewById(R.id.img_homeindex);
		img_bespeak = (ImageView) findViewById(R.id.img_bespeak);
		img_presonal = (ImageView) findViewById(R.id.img_presonal);

		text_homeindex = (TextView) findViewById(R.id.text_homeindex);
		text_bespeak = (TextView) findViewById(R.id.text_bespeak);
		text_presonal = (TextView) findViewById(R.id.text_presonal);
	}

	public void updateContent(int id) {

		try {
			Fragment fragment = null;
			String tag = null;
			final FragmentManager fm = getSupportFragmentManager();
			final FragmentTransaction tr = fm.beginTransaction();
			if (currentContentFragmentTag != null) {
				final Fragment currentFragment = fm.findFragmentByTag(currentContentFragmentTag);
				if (currentFragment != null) {
					tr.hide(currentFragment);// 将当前的Frament隐藏到后台去
				}
			}
			switch (id) {
			case R.id.homeindex:
				tag = HairdressingForUserIndex.class.getSimpleName();
				final Fragment hairdressing = fm.findFragmentByTag(tag);
				if (hairdressing != null) {
					fragment = hairdressing;
				} else {
					fragment = new HairdressingForUserIndex();
				}
				Hairdressing = (HairdressingForUserIndex) fragment;
				break;
			case R.id.bespeak:
				tag = BespeakIndex.class.getSimpleName();
				final Fragment bespeak = fm.findFragmentByTag(tag);
				if (bespeak != null) {
					fragment = bespeak;
					Bespeak = (BespeakIndex) fragment;
				} else {
					fragment = new BespeakIndex();
				}

				Bespeak = (BespeakIndex) fragment;
				break;

			case R.id.presonal:
				tag = PersonalForUserIndex.class.getSimpleName();
				final Fragment personal = fm.findFragmentByTag(tag);
				if (personal != null) {
					fragment = personal;
				} else {
					fragment = new PersonalForUserIndex();
				}
				personalIndex = (PersonalForUserIndex) fragment;
				break;
			default:
				tag = HairdressingForUserIndex.class.getSimpleName();
				final Fragment hairdressing1 = fm.findFragmentByTag(tag);
				if (hairdressing1 != null) {
					fragment = hairdressing1;
				} else {
					fragment = new HairdressingForUserIndex();
				}
				Hairdressing = (HairdressingForUserIndex) fragment;
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

	private void removeAllFragment() {

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction tr = fm.beginTransaction();
		Fragment Hairdressing = fm.findFragmentByTag(HairdressingForUserIndex.class.getSimpleName());
		if (Hairdressing != null && Hairdressing.isAdded()) {
			tr.hide(Hairdressing);
			tr.remove(Hairdressing);
		}
		Fragment Bespeak = fm.findFragmentByTag(BespeakIndex.class.getSimpleName());
		if (Bespeak != null && Bespeak.isAdded()) {
			tr.hide(Bespeak);
			tr.remove(Bespeak);
		}
		Fragment personal = fm.findFragmentByTag(PersonalForUserIndex.class.getSimpleName());
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
			text_homeindex.setSelected(true);
			text_bespeak.setSelected(false);
			text_presonal.setSelected(false);
			img_homeindex.setSelected(true);
			img_bespeak.setSelected(false);
			img_presonal.setSelected(false);
			updateContent(checkid);
			break;
		case R.id.bespeak:
			text_homeindex.setSelected(false);
			text_bespeak.setSelected(true);
			text_presonal.setSelected(false);
			img_homeindex.setSelected(false);
			img_bespeak.setSelected(true);
			img_presonal.setSelected(false);
			updateContent(checkid);
			break;
		case R.id.presonal:
			text_homeindex.setSelected(false);
			text_bespeak.setSelected(false);
			text_presonal.setSelected(true);
			img_homeindex.setSelected(false);
			img_bespeak.setSelected(false);
			img_presonal.setSelected(true);
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
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				// Intent intent = new Intent(Intent.ACTION_MAIN);
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				// intent.addCategory(Intent.CATEGORY_HOME);
				// startActivity(intent);
				app.finishAll();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (currentId != id) {
			changeUI(id);
		}
	}

}
