package com.loopfire.meitaotao.function.register;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.api.UserAPI;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.entity.RegisterEntity;
import com.loopfire.meitaotao.receiver.SMSBroadcastReceiver;
import com.loopfire.meitaotao.receiver.SMSBroadcastReceiver.MessageListener;

public class RegisterActivity extends BaseActivity {
	private TextView tv_user, tv_barber, tv_identifyCode, tv_gain_identifyCode;
	private EditText et_phoneNum, et_identifyCode, et_inviteCode;
	private Button bt_next;
	private int currentUser = 0; // 0用户，1理发师
	// 这是请求码，为了方便识别URL
	private final int REQUEST_CODE = 101;
	MyAsyncTask task = null;
	private UserAPI api;
	private SMSBroadcastReceiver smsReceiver;
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		tv_user = (TextView) findViewById(R.id.tv_user);
		tv_barber = (TextView) findViewById(R.id.tv_barber);
		bt_next = (Button) findViewById(R.id.signup_btn_next);
		tv_gain_identifyCode = (TextView) findViewById(R.id.tv_gain_identifyCode);
		et_identifyCode = (EditText) findViewById(R.id.et_identifyCode);
		et_phoneNum = (EditText) findViewById(R.id.et_phoneNum);
		et_inviteCode = (EditText) findViewById(R.id.et_inviteCode);

	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		tv_barber.setOnClickListener(this);
		tv_user.setOnClickListener(this);
		bt_next.setOnClickListener(this);
		tv_gain_identifyCode.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		final Integer flag = Integer.parseInt(param[0].toString());
		Log.i("refresh code", flag.toString());
		switch (flag) {
		case REQUEST_CODE:
			Log.i("", "[refresh] data:" + param[1].toString());
			try {
				JSONObject object = new JSONObject(param[1].toString());
				Log.i("", "messgae:" + object.optString("message"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		api = new UserAPI();
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_user:
			if (currentUser != 0) {
				tv_user.setBackgroundResource(R.drawable.signup_signup_btn_bg_pressed_left);
				tv_user.setTextColor(getResources().getColor(R.color.white));
				tv_barber.setBackgroundDrawable(null);
				tv_barber.setTextColor(getResources().getColor(
						R.color.text_grey));
				currentUser = 0;
			}
			break;
		case R.id.tv_barber:
			if (currentUser != 1) {
				tv_barber
						.setBackgroundResource(R.drawable.signup_signup_btn_bg_pressed_right);
				tv_barber.setTextColor(getResources().getColor(R.color.white));
				tv_user.setBackgroundDrawable(null);
				tv_user.setTextColor(getResources().getColor(R.color.text_grey));
				currentUser = 1;
			}
			break;
		case R.id.signup_btn_next:
			goEnterPwd();

			break;
		case R.id.tv_gain_identifyCode:
			gainIdentifyCode();
			break;
		}

	}

	/*
	 * 跳到输入密码
	 */
	private void goEnterPwd() {
		String phoneNum = et_phoneNum.getText().toString().trim();
		String identifyCode = et_identifyCode.getText().toString().trim();
		if (TextUtils.isEmpty(phoneNum)) {
			Toast.makeText(getApplicationContext(), "请输入手机号",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(identifyCode)) {
			Toast.makeText(getApplicationContext(), "请输入验证码",
					Toast.LENGTH_SHORT).show();
			return;
		}
		RegisterEntity entity = new RegisterEntity(phoneNum, identifyCode);
		Intent intent = new Intent(this, EntryPwdActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("entity", entity);
		intent.putExtras(bundle);
		startActivity(intent);

	}

	/**
	 * 获取验证码
	 */
	private void gainIdentifyCode() {
		String phoneNum = et_phoneNum.getText().toString().trim();
		if (TextUtils.isEmpty(phoneNum)) {
			Toast.makeText(getApplicationContext(), "请输入手机号",
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			registerSMSReceiver(phoneNum);
		}
		api.getIdentifyCode(phoneNum, RegisterActivity.this, REQUEST_CODE);
		task = new MyAsyncTask(tv_gain_identifyCode);
		task.execute(1000);

	}

	private void registerSMSReceiver(String phoneNum) {
		if (smsReceiver != null) {
			unregisterReceiver(smsReceiver);
		}
		{
			smsReceiver = new SMSBroadcastReceiver(new MessageListener() {
				@Override
				public void onReceived(String msg) {
					et_identifyCode.setText(msg);

				}
			}, phoneNum);
			IntentFilter filter = new IntentFilter();
			filter.setPriority(Integer.MAX_VALUE);
			filter.addAction("android.provider.Telephony.SMS_RECEIVED");
			registerReceiver(smsReceiver, filter);
		}

	}

	/*
	 * 倒计时任务类
	 */
	private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
		private TextView tv_gain_identifyCode;

		@Override
		protected String doInBackground(Integer... params) {
			int i = 29;
			for (; i >= 0; i--) {
				publishProgress(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return i + "";
		}

		public MyAsyncTask(TextView tv_gain_identifyCode) {
			super();
			this.tv_gain_identifyCode = tv_gain_identifyCode;
		}

		@Override
		protected void onPreExecute() {
			tv_gain_identifyCode.setClickable(false);
		}

		@Override
		protected void onPostExecute(String result) {
			tv_gain_identifyCode.setText("点击重试");
			tv_gain_identifyCode.setClickable(true);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			tv_gain_identifyCode.setText("倒计时" + values[0] + "秒");
		}
	}

	@Override
	public void finish() {
		super.finish();
		if (smsReceiver != null) {
			unregisterReceiver(smsReceiver);
		}
	}

}
