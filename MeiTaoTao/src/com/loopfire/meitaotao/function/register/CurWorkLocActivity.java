package com.loopfire.meitaotao.function.register;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.api.BarberAPI;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.entity.BarberLevel;
import com.loopfire.meitaotao.function.SelectTimeActivity;
import com.loopfire.meitaotao.function.barber.BarberLevelSelectActivity;
import com.loopfire.meitaotao.function.common.DialogCenterHintActivity;
import com.loopfire.meitaotao.util.FileUtils;
import com.loopfire.meitaotao.util.Util;

/**
 * 理发师审核 最近一次工作地
 * 
 * @author FLT
 */
public class CurWorkLocActivity extends BaseActivity {

	private final String TAG = "CurWorkLocActivity";

	private final int RESULT_VALUS = 777;

	private final int CODE_START_JOB_TIME = 201;
	private final int CODE_END_JOB_TIME = 202;
	private final int CODE_JOB_ADDRESS = 203;
	private final int CODE_LEVEL = 204;
	private final int CODE_CERTIFIER_NAME = 205;
	private final int CODE_CERTIFIER_PHONE = 206;

	private Button btn_commit;
	private ImageView iv_witnesserName;
	private BarberAPI api;
	private final int REQUEST_CODE = 101;
	private TextView tv_start_time_job;
	private TextView tv_end_time_job;
	private TextView tv_job_last_address;
	private TextView tv_level;
	private TextView tv_name;
	private TextView tv_phone;
	private int level;

	private RelativeLayout rl_start_job_time, rl_end_job_time, rl_job_address,
			rl_level, rl_certifier_name, rl_certifier_phone;

	private String begin;
	private String end;
	private String address;
	private String level_value;
	private String name;
	private String phone;
	private BarberLevel mBarberLevel;

	@Override
	public void initView() {
		super.initView();
		setTitle("最近一次工作地");
		btn_commit = (Button) findViewById(R.id.btn_commit);
		iv_witnesserName = (ImageView) findViewById(R.id.iv_witnesserName);
		// 这个是
		tv_start_time_job = (TextView) findViewById(R.id.tv_start_time_job);
		tv_end_time_job = (TextView) findViewById(R.id.tv_end_time_job);
		tv_job_last_address = (TextView) findViewById(R.id.tv_job_last_address);
		tv_level = (TextView) findViewById(R.id.tv_level);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_phone = (TextView) findViewById(R.id.tv_phone);

		//
		rl_start_job_time = (RelativeLayout) findViewById(R.id.rl_start_job_time);
		rl_end_job_time = (RelativeLayout) findViewById(R.id.rl_end_job_time);
		rl_job_address = (RelativeLayout) findViewById(R.id.rl_job_address);
		rl_level = (RelativeLayout) findViewById(R.id.rl_level);
		rl_certifier_name = (RelativeLayout) findViewById(R.id.rl_certifier_name);
		rl_certifier_phone = (RelativeLayout) findViewById(R.id.rl_certifier_phone);

	}

	@Override
	public void initListener() {
		super.initListener();
		btn_commit.setOnClickListener(this);
		iv_witnesserName.setOnClickListener(this);
		button_left.setOnClickListener(this);

		rl_start_job_time.setOnClickListener(this);
		rl_end_job_time.setOnClickListener(this);
		rl_job_address.setOnClickListener(this);
		rl_level.setOnClickListener(this);
		rl_certifier_name.setOnClickListener(this);
		rl_certifier_phone.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cur_work_location);
		initView();
		initListener();
		api = new BarberAPI();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.title_Left:
			finish();
			break;
		case R.id.btn_commit:
			// 点击提交按钮
			commit();
			break;
		case R.id.rl_start_job_time:
			begin = tv_start_time_job.getText().toString();
			Intent startTimeIntent = new Intent(this, SelectTimeActivity.class);
			startTimeIntent.putExtra("request_value", "" + begin);
			startTimeIntent.putExtra("title", "工作开始时间");
			startTimeIntent.putExtra("code", CODE_START_JOB_TIME);
			startActivityForResult(startTimeIntent, CODE_START_JOB_TIME);
			break;
		case R.id.rl_end_job_time:
			end = tv_end_time_job.getText().toString();
			Intent endTimeIntent = new Intent(this, SelectTimeActivity.class);
			endTimeIntent.putExtra("request_value", "" + end);
			endTimeIntent.putExtra("title", "工作结束时间");
			endTimeIntent.putExtra("code", CODE_END_JOB_TIME);
			endTimeIntent.putExtra("code", CODE_END_JOB_TIME);
			startActivityForResult(endTimeIntent, CODE_END_JOB_TIME);
			break;
		case R.id.rl_job_address:
			address = tv_job_last_address.getText().toString();
			Intent lastAddressIntetn = new Intent(this, EntryTextActivity.class);
			lastAddressIntetn.putExtra("request_value", "" + address);
			lastAddressIntetn.putExtra("title", "工作地点");
			lastAddressIntetn.putExtra("code", CODE_JOB_ADDRESS);
			lastAddressIntetn.putExtra("inputType", InputType.TYPE_CLASS_TEXT);
			startActivityForResult(lastAddressIntetn, CODE_JOB_ADDRESS);
			break;
		case R.id.rl_level:
			level_value = tv_level.getText().toString();
			Intent levelIntetn = new Intent(this,
					BarberLevelSelectActivity.class);
			levelIntetn.putExtra("request_value", "" + level_value);
			levelIntetn.putExtra("title", "级别");
			levelIntetn.putExtra("code", CODE_LEVEL);
			startActivityForResult(levelIntetn, CODE_LEVEL);
			break;
		case R.id.rl_certifier_name:
			name = tv_name.getText().toString();
			Intent nameIntetn = new Intent(this, EntryTextActivity.class);
			nameIntetn.putExtra("request_value", "" + name);
			nameIntetn.putExtra("title", "证明人姓名");
			nameIntetn.putExtra("inputType", InputType.TYPE_CLASS_TEXT);
			nameIntetn.putExtra("code", CODE_CERTIFIER_NAME);
			startActivityForResult(nameIntetn, CODE_CERTIFIER_NAME);
			break;
		case R.id.rl_certifier_phone:
			phone = tv_phone.getText().toString();
			Intent phoneIntent = new Intent(this, EntryTextActivity.class);
			phoneIntent.putExtra("request_value", "" + phone);
			phoneIntent.putExtra("title", "证明人手机号码");
			phoneIntent.putExtra("inputType", InputType.TYPE_CLASS_PHONE);
			phoneIntent.putExtra("code", CODE_CERTIFIER_PHONE);
			startActivityForResult(phoneIntent, CODE_CERTIFIER_PHONE);
			break;
		}
	}

	/*
	 * 提交请求
	 */
	private void commit() {
		begin = tv_start_time_job.getText().toString();
		end = tv_end_time_job.getText().toString();
		address = tv_job_last_address.getText().toString();
		name = tv_name.getText().toString();
		phone = tv_phone.getText().toString();
		if (Util.isEmpty(begin)) {
			Util.toastInfo(getApplicationContext(), "请输入工作开始时间");
			return;
		}
		if (Util.isEmpty(end)) {
			Util.toastInfo(getApplicationContext(), "请输入工作结束时间");
			return;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateBegin = format.parse(begin);
			Date dateEnd = format.parse(end);
			if (!dateBegin.before(dateEnd)) {
				Util.toastInfo(getApplicationContext(), "工作开始时间不能后于工作结束时间");
				return;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (Util.isEmpty(address)) {
			Util.toastInfo(getApplicationContext(), "请输入工作地点");
			return;
		}
		if (!Util.isEmpty(mBarberLevel)) {
			level_value = mBarberLevel.getDutyId();
		} else {
			Util.toastInfo(getApplicationContext(), "请选择理发师级别");
			return;
		}
		if (Util.isEmpty(name)) {
			Util.toastInfo(getApplicationContext(), "请输入证明人姓名");
			return;
		}
		if (Util.isEmpty(phone)) {
			Util.toastInfo(getApplicationContext(), "请输入证明人手机号码");
			return;
		}
		String userId = FileUtils.readuserInfo(getApplicationContext())
				.getUserId();
		api.getBarberLastWork(userId, begin, end, address, level_value, name,
				phone, CurWorkLocActivity.this, REQUEST_CODE);
	}

	private void ToastInfo(String str) {

	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		final Integer request_code = Integer.parseInt(param[0].toString());

		if (request_code == REQUEST_CODE) {
			JSONObject jObject = JSONObject.parseObject(param[1].toString());
			Log.i("object", jObject + "");
			final int code = jObject.getInteger("code");
			if (code == 0) {
				startActivity(new Intent(this, DialogCenterHintActivity.class));
			} else {

			}
		} else {
			// 请求失败
			return;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			final String value = data.getStringExtra("result_value");
			if (Util.isEmpty(value)) {
				return;
			}
			switch (resultCode) {
			case CODE_START_JOB_TIME:
				tv_start_time_job.setText("" + value);
				break;
			case CODE_END_JOB_TIME:
				tv_end_time_job.setText("" + value);
				break;
			case CODE_JOB_ADDRESS:
				tv_job_last_address.setText("" + value);
				break;
			case CODE_LEVEL:
				mBarberLevel = (BarberLevel) data.getSerializableExtra("level");
				tv_level.setText("" + value);
				break;
			case CODE_CERTIFIER_NAME:
				tv_name.setText("" + value);
				break;
			case CODE_CERTIFIER_PHONE:
				tv_phone.setText("" + value);
				break;
			default:
				break;
			}
		}
	}

}
