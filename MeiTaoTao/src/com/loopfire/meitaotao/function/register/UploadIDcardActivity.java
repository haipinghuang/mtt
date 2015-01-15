package com.loopfire.meitaotao.function.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
/**
 * 理发师审核
 * 上传身份证
 * @author Administrator
 *
 */
public class UploadIDcardActivity extends BaseActivity{
	private ImageView idcard;
	private Button bt_next;
	@Override
	public void initView() {
		super.initView();
		idcard=(ImageView) findViewById(R.id.iv_idcard);
		bt_next=(Button) findViewById(R.id.btn_next);
	}

	@Override
	public void initListener() {
		super.initListener();
		bt_next.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_idcard);
		initView();
		initListener();
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.btn_next:
			startActivity(new Intent(this, CurWorkLocActivity.class));
			break;
		}
	}
}
