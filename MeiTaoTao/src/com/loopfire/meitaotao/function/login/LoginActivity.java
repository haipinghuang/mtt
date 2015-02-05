package com.loopfire.meitaotao.function.login;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopfire.meitaotao.BarberMainActivity;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.UserMainActivity;
import com.loopfire.meitaotao.api.BarberAPI;
import com.loopfire.meitaotao.api.BaseAPI;
import com.loopfire.meitaotao.api.UserAPI;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.entity.UserInfo;
import com.loopfire.meitaotao.function.register.RegisterActivity;
import com.loopfire.meitaotao.function.register.UploadIDcardActivity;
import com.loopfire.meitaotao.util.FileUtils;
import com.loopfire.meitaotao.util.Util;

public class LoginActivity extends BaseActivity {
	private TextView tv_login;
	private TextView tv_go_register;
	private TextView tv_forget_pwd;
	private EditText phoneNum;
	private EditText password;
	private UserAPI api;
	private static final int LOGIN = 101;

	@Override
	public void initView() {
		api = new UserAPI();
		tv_login = (TextView) findViewById(R.id.tv_login);
		tv_go_register = (TextView) findViewById(R.id.tv_go_register);
		tv_forget_pwd = (TextView) findViewById(R.id.tv_forget_pwd);
		phoneNum = (EditText) findViewById(R.id.phoneNum);
		password = (EditText) findViewById(R.id.password);
	}

	@Override
	public void initListener() {
		tv_login.setOnClickListener(this);
		tv_go_register.setOnClickListener(this);
		tv_forget_pwd.setOnClickListener(this);
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		final int flag = Integer.parseInt(param[0].toString());
		switch (flag) {
		case LOGIN:
			JSONObject object = null;
			int code = 100;
			String msg = null;
			try {
				object = new JSONObject(param[1].toString());
				code = object.getInt("code");
				msg = object.getString("message");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			switch (code) {
			case 0:
				UserInfo user = null;
				try {
					object = object.getJSONObject("rows");
					String userId = object.getString("userId");
					String userAccount = object.getString("userAccount");
					String userNickname = object.getString("userNickname");
					String userAddress = object.getString("userAddress");
					String userPortrait = object.getString("userPortrait");
					String userName = object.getString("userName");
					user = new UserInfo(userId, userName, userAccount, userNickname, userAddress, userPortrait);
					FileUtils.saveuserInfo(getApplicationContext(), user);
					String id = FileUtils.readuserInfo(getApplicationContext()).getUserId();
					Log.i("id", id);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// Util.toastInfo(getApplicationContext(), "登录成功");
				startActivity(new Intent(this, UserMainActivity.class));
				break;
			default:
				Util.toastInfo(getApplicationContext(), msg);
				break;
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
		setContentView(R.layout.activity_login);
		initView();
		initListener();
		/*
		 * 下面做测试接口用
		 */
		{
			BarberAPI api = new BarberAPI();
			// api.getBarberLevel(this, 1001);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {

		case R.id.tv_go_register:
			startActivity(new Intent(this, RegisterActivity.class));
			break;
		case R.id.tv_forget_pwd:
			/*
			 * 忘记密码； 此处作为测试个activity入口，后面要改
			 */
			startActivity(new Intent(this, BarberMainActivity.class));
			break;
		default:
			// 发送登录数据
			login();
			break;
		}
	}

	private void login() {
		String phoneNo = phoneNum.getText().toString().trim();
		String pwd = password.getText().toString().trim();
		if (phoneNo.length() != 11) {
			Util.toastInfo(getApplicationContext(), "手机号格式不正确");
			return;
		}
		if (pwd.length() < 6) {
			Util.toastInfo(getApplicationContext(), "密码设置过于简单");
			return;
		}
		api.userLogin(phoneNo, pwd, this, LOGIN);
	}
}
