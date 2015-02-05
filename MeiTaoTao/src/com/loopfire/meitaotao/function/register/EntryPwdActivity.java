package com.loopfire.meitaotao.function.register;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.UserMainActivity;
import com.loopfire.meitaotao.api.UserAPI;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.entity.RegisterEntity;
import com.loopfire.meitaotao.function.login.LoginActivity;
import com.loopfire.meitaotao.util.Util;

public class EntryPwdActivity extends BaseActivity {
	private static final int REGISTER = 101;
	private EditText et_password;
	private Button bt_next;
	private UserAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_pwd);
		initView();
		initListener();
		setTitle("输入密码");
	}

	@Override
	public void initListener() {
		super.initListener();
		bt_next.setOnClickListener(this);
	}

	@Override
	public void initView() {
		super.initView();
		api = new UserAPI();
		bt_next = (Button) findViewById(R.id.signup_btn_next);
		et_password = (EditText) findViewById(R.id.et_password);
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		final int flag = Integer.parseInt(param[0].toString());
		Log.i("refresh code", flag + "");
		switch (flag) {
		case REGISTER:
			showResult(param);
			break;
		}
	}

	private void showResult(Object... param) {
		JSONObject jsonObject;
		int code = 2;
		String msg = null;
		try {
			jsonObject = new JSONObject(param[1].toString());
			code = jsonObject.getInt("code");
			msg = jsonObject.getString("message");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		switch (code) {
		case 0:
			Util.toastInfo(EntryPwdActivity.this, "恭喜你注册成功");
			startActivity(new Intent(this, LoginActivity.class));
			break;
		default:
			Util.toastInfo(EntryPwdActivity.this, msg);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.signup_btn_next:
			register();
			break;
		}

	}

	/**
	 * 发送注册请求
	 */
	private void register() {

		RegisterEntity entity = (RegisterEntity) getIntent()
				.getSerializableExtra("entity");
		String pwd = et_password.getText().toString().trim();
		if (TextUtils.isEmpty(pwd)) {
			Util.toastInfo(getApplicationContext(), "请输入密码");
			return;
		}
		String userAccount = entity.getPhoneNum();
		String identifyCode = entity.getIdentifyCode();
		api.register(userAccount, pwd, identifyCode, null,
				EntryPwdActivity.this, REGISTER);

	}
}
