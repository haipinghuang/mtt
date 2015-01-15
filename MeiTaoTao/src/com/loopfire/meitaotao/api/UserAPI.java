package com.loopfire.meitaotao.api;


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
	public void login(String user, String paw, final BaseActivity listener,final int requestCode) {
		RequestParams params = new RequestParams();
		params.put("uname", user);
		params.put("passwd", paw);
		request("&a=login&m=member", params, HTTPMETHOD_POST, true, listener,requestCode);
	}
}
