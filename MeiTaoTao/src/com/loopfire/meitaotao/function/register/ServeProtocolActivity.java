package com.loopfire.meitaotao.function.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;

public class ServeProtocolActivity extends BaseActivity {
	private WebView webView;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		webView = (WebView) findViewById(R.id.webView);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serve_protocol);
		initView();
		initListener();
		setTitle("服务协议");
		setRightBackground(R.drawable.signup_location_arrow2);
		setRight(null);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.top_right_linear:
			/*
			 * 目前跳过上传身份证，以便测试
			 */
			// startActivity(new Intent(getApplicationContext(),
			// UploadIDcardActivity.class));
			startActivity(new Intent(getApplicationContext(),
					CurWorkLocActivity.class));
			break;

		default:
			break;
		}
	}

}
