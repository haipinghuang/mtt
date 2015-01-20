package com.loopfire.meitaotao.function.barber.my;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.entity.MyMessage;
import com.loopfire.meitaotao.function.barber.adapter.MyMsgAdapter;

/**
 * 理发师--我的--消息
 * 
 * @author Administrator
 * 
 */
public class MyMsgForBarberActivity extends BaseActivity {
	private ListView listView;
	private MyMsgAdapter msgAdapter;
	private List<MyMessage> msgList = new ArrayList<MyMessage>();

	@Override
	public void initView() {
		super.initView();

		for (int i = 0; i < 4; i++) {
			MyMessage msg = new MyMessage("ketty" + i, "hello ketty" + i,
					"2011-11-11 09:0" + i);
			msgList.add(msg);
		}
		listView = (ListView) findViewById(R.id.listView);
		msgAdapter = new MyMsgAdapter(msgList, this);
		listView.setAdapter(msgAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(MyMsgForBarberActivity.this,
						MsgDetailForBarberActivity.class));
			}
		});
	}

	@Override
	public void initListener() {
		super.initListener();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barber_my_mymsg);
		initView();
		initListener();

		setTitle(getString(R.string.my_msg));
		displayRight();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		}
	}

}
