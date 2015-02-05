package com.loopfire.meitaotao.api;

import java.io.File;
import java.io.FileNotFoundException;

import android.text.TextUtils;

import com.loopfire.meitaotao.common.BaseActivity;
import com.loopj.android.http.RequestParams;

/**
 * 用户登录注册等操作api
 * 
 */
public class UserAPI extends BaseAPI {

	/**
	 * 登录已完成
	 * 
	 * @param user
	 * @param paw
	 * @param listener
	 * @param requestCode
	 */
	public void login(String user, String paw, final BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		params.put("uname", user);
		params.put("passwd", paw);
		request("&a=login&m=member", params, HTTPMETHOD_POST, true, listener, requestCode);
	}

	/**
	 * 获取验证码
	 * 
	 * @param mobile
	 * @param listener
	 * @param requestCode
	 */
	public void getIdentifyCode(final String mobile, final BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		params.put("mobile", mobile);
		request("/webService/sms/sendSms", params, HTTPMETHOD_POST, false, listener, requestCode);
	}

	public void register(final String userAccount, final String userPassword, final String verificationCode, String invitationCode, final BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		params.put("userAccount", userAccount);
		params.put("userPassword", userPassword);
		params.put("verificationCode", verificationCode);
		if (!TextUtils.isEmpty(invitationCode)) {
			params.put("invitationCode", invitationCode);
		}
		request("/webService/user/regist", params, HTTPMETHOD_POST, true, listener, requestCode);
	}

	/**
	 * 用户登录
	 * 
	 * @param userAccount
	 * @param userPassword
	 * @param verificationCode
	 * @param listener
	 * @param requestCode
	 */
	public void userLogin(final String userAccount, final String userPassword, final BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		params.put("userAccount", userAccount);
		params.put("userPassword", userPassword);
		request("/webService/user/login", params, HTTPMETHOD_POST, true, listener, requestCode);
	}

	/**
	 * 上传身份证
	 * 
	 * @param userId
	 * @param type
	 * @param identity
	 * @param listener
	 * @param requestCode
	 */
	public void uploadIdcard(final String userId, final int type, File identity, final BaseActivity listener, final int requestCode) {
		RequestParams params = new RequestParams();
		params.put("userId", userId);
		params.put("type", type + "");
		try {
			params.put("identity", identity);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		request("/webService/identity/identityUpload", params, HTTPMETHOD_POST, true, listener, requestCode);
	}

}
