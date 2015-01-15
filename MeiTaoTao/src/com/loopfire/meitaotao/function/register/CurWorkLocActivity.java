package com.loopfire.meitaotao.function.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.function.common.DialogCenterHintActivity;

/**
 * 理发师审核 最近一次工作地
 * 
 * @author Administrator
 * 
 */
public class CurWorkLocActivity extends BaseActivity {
	private Button btn_commit;
	private ImageView iv_witnesserName;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		btn_commit = (Button) findViewById(R.id.btn_commit);
		iv_witnesserName = (ImageView) findViewById(R.id.iv_witnesserName);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		super.initListener();
		btn_commit.setOnClickListener(this);
		iv_witnesserName.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cur_work_location);
		initView();
		initListener();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_commit:
			startActivity(new Intent(this, DialogCenterHintActivity.class));
			break;
		case R.id.iv_witnesserName:
			startActivity(new Intent(this, EntryTextActivity.class));
			break;
		}
	}

}
