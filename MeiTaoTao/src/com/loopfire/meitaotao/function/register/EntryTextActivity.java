package com.loopfire.meitaotao.function.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.util.Util;

/**
 * 理发师审核 输入各种文本信息
 * 
 * @author Administrator
 * 
 */
public class EntryTextActivity extends BaseActivity {
	private EditText et_input;
	private int REQUEST_CODE;
	String title;
	int inputType = -1;

	@Override
	public void initView() {
		super.initView();
		et_input = (EditText) findViewById(R.id.et_input);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_text);
		initView();
		initListener();
		title = getIntent().getStringExtra("title");
		setTitle(title);
		setRight("确定");
		REQUEST_CODE = getIntent().getExtras().getInt("code");
		inputType = getIntent().getIntExtra("inputType",
				InputType.TYPE_CLASS_TEXT);
		et_input.setInputType(inputType);

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.top_right_linear:
			String value = et_input.getText().toString().trim();
			if (!TextUtils.isEmpty(value)) {
				if (inputType == InputType.TYPE_CLASS_PHONE) {
					if (value.length() != 11) {
						Util.toastInfo(getApplicationContext(), title
								+ "格式不争取，请重新输入");
						return;
					}
				}
				Intent result = new Intent();
				result.putExtra("result_value", value);
				setResult(REQUEST_CODE, result);
				finish();
			} else {
				Util.toastInfo(getApplicationContext(), "请输入" + title);
			}
			break;
		default:
			break;
		}

	}

}
