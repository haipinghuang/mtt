package com.loopfire.meitaotao.api;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.common.BaseFragment;
import com.loopfire.meitaotao.common.BaseFragmentActivity;
import com.loopfire.meitaotao.util.Util;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

/**
 * API的基类，每个接口类都继承了此抽象类
 * 
 * @author mobile
 */
public abstract class BaseAPI {

	// public static final String API_SERVER =
	// "http://192.168.1.9/mobileapi/index.php";
	public static final String API_SERVER = "http://192.168.1.22:80/hairdressing";

	public static AsyncHttpClient client = null;
	public static PersistentCookieStore cookieStore;

	public static final String HTTPMETHOD_POST = "POST"; // post请求方式
	public static final String HTTPMETHOD_GET = "GET";// get请求方式
	private Dialog mDialog;

	public BaseAPI() {

	}

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static String getAbsoluteUrl(String relativeUrl) {
		// Log.e("club====>",API_SERVER + relativeUrl+"");
		return API_SERVER + relativeUrl;
	}

	/**
	 * 请求无设置超时时间(默认值)
	 * 
	 * @param url
	 *            模块字段
	 * @param params
	 *            参数
	 * @param postMethod
	 *            Post或Get方法
	 * @param dealing
	 *            是否弹出等待处理框
	 * @param listener
	 *            回调的activity
	 * @param requestCode
	 *            回调的请求码
	 */

	protected void request(final String url, final RequestParams params,
			String postMethod, final boolean showDealing,
			final BaseActivity listener, final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();
			client.setTimeout(45000);
		}

		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener)
									.create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						// Log.d("params", params.toString());
						// Log.d("url", url);
						Log.d("onSuccess", response);
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					Log.i("[onFailure]:", response);
					if (response.contains("code")) {
						listener.refresh(requestCode, response);
					} else {
						Util.toastInfo(listener, "连接超时");
						listener.refresh(requestCode, BaseActivity.ERROR);
					}
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	/**
	 * 请求设置超时时间
	 * 
	 * @param url
	 *            模块字段
	 * @param params
	 *            参数
	 * @param postMethod
	 *            Post或Get方法
	 * @param dealing
	 *            是否弹出等待处理框
	 * @param listener
	 *            回调的activity
	 * @param requestCode
	 *            回调的请求码
	 * @param timeout
	 *            连接超时时间,调用需要新建子类API来调用此方法
	 */

	protected void request(final String url, final RequestParams params,
			String postMethod, final boolean showDealing,
			final BaseActivity listener, final int requestCode, int timeout) {
		if (client == null) {
			client = new AsyncHttpClient();
		}

		if (timeout <= 0) {
			timeout = 45000;
		}
		client.setTimeout(timeout);
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener)
									.create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						Log.d("params", params.toString());
						Log.d("url", url);
						Log.d("response", response);
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					Log.i("", "BaseActivity [onFailure]:" + response);
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	protected void request(final String url, final RequestParams params,
			String postMethod, final boolean showDealing,
			final BaseFragment listener, final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();
			client.setTimeout(45000);
		}
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener
									.getContext()).create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {
							Log.e("dialog_excption", "dialog_excption");
						}
					}
				}

				public void onSuccess(String response) {
					try {
						if (params != null) {
							Log.d("params", params.toString());
						}
						Log.d("url", url);
						Log.d("response", response);
						// Toast.makeText(listener.getContext(),"response"+response,
						// Toast.LENGTH_LONG).show();
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					Log.e("onFailure", "" + response);
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	/**
	 * 请求无监听
	 * 
	 * @param url
	 * @param params
	 * @param postMethod
	 * @param liste
	 */
	protected void requestNoLister(final String url,
			final RequestParams params, String postMethod,
			final MessageListener liste) {
		if (client == null) {
			client = new AsyncHttpClient();
		}
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {

				}

				public void onSuccess(String response) {
					Log.d("count post url", url);
					Log.d("count post params", params.toString());
					Log.d("count post Success", response);
					liste.OnMessageGet(response);
				}

				public void onFailure(Throwable e, String response) {

				}

				public void onFinish() {

				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {

				}

				public void onSuccess(String response) {
					liste.OnMessageGet(response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();

				}

				public void onFinish() {

				}
			});
		}
	}

	public interface MessageListener {
		public void OnMessageGet(String data);
	}

	protected void requestforframeactivity(final String url,
			final RequestParams params, String postMethod,
			final boolean showDealing, final BaseFragmentActivity listener,
			final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();
			client.setTimeout(45000);
		}
		if (postMethod.equals(HTTPMETHOD_POST)) {
			BaseAPI.post(url, params, new AsyncHttpResponseHandler() {
				public void onStart() {
					if (showDealing) {
						try {
							mDialog = new AlertDialog.Builder(listener)
									.create();
							mDialog.show();
							mDialog.setContentView(R.layout.loading_process_dialog_anim);
						} catch (Exception ex) {

						}
					}
				}

				public void onSuccess(String response) {
					try {
						Log.d("params", params.toString());
						Log.d("url", url);
						Log.d("response", response);
						JSONObject obj = new JSONObject(response);
						/* 登录信息保存 end */
						int state = obj.getInt("ret");
						if (state == BaseActivity.COOKIE_INVILD) {
							listener.refresh(BaseActivity.COOKIE_INVILD);
						} else {
							listener.refresh(requestCode, response);
						}
					} catch (Exception ex) {
						listener.refresh(requestCode, response);
						ex.printStackTrace();
					}
				}

				public void onFailure(Throwable e, String response) {
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
					if (showDealing) {
						try {
							if (mDialog != null) {
								mDialog.cancel();
								mDialog = null;
							}
						} catch (Exception ex) {

						}
					}
				}
			});
		} else {
			BaseAPI.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {
				}

				public void onSuccess(String response) {
					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {
				}
			});
		}
	}

	protected void request(final String url, final RequestParams params,
			String postMethod, final BaseActivity listener,
			final int requestCode) {
		if (client == null) {
			client = new AsyncHttpClient();

		}

		if (postMethod.equals(HTTPMETHOD_POST)) {

			client.post(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {

				}

				public void onSuccess(String response) {

					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {

				}

			});

		} else {
			client.get(url, params, new AsyncHttpResponseHandler() {

				public void onStart() {

				}

				public void onSuccess(String response) {

					listener.refresh(requestCode, response);
				}

				public void onFailure(Throwable e, String response) {
					e.printStackTrace();
					listener.refresh(BaseActivity.ERROR);
				}

				public void onFinish() {

				}
			});
		}
	}

}
