package com.loopfire.meitaotao.function.barber.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.entity.MyMessage;

/**
 * 理发师-我的消息adapter
 * 
 * @author Administrator
 * 
 */
public class MyMsgAdapter extends BaseAdapter {
	private List<MyMessage> msgList;
	private Context contex;

	public MyMsgAdapter(List<MyMessage> msgList, Context contex) {
		super();
		this.msgList = msgList;
		this.contex = contex;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(contex).inflate(
					R.layout.barber_my_msg_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.userName = (TextView) convertView
					.findViewById(R.id.userName);
			viewHolder.msg_body = (TextView) convertView
					.findViewById(R.id.msg_body);
			viewHolder.receive_time = (TextView) convertView
					.findViewById(R.id.receive_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.userName.setText(getItem(position).getUserName());
		viewHolder.msg_body.setText(getItem(position).getMsg_body());
		viewHolder.receive_time.setText(getItem(position).getReceive_time());
		return convertView;
	}

	public class ViewHolder {
		private TextView userName;
		private TextView msg_body;
		private TextView receive_time;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return msgList.size();
	}

	@Override
	public MyMessage getItem(int position) {
		// TODO Auto-generated method stub
		return msgList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}
