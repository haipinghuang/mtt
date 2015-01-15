package com.loopfire.meitaotao.function.barber.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListViewAdapter extends BaseAdapter{
	private List<View> views;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}
	
	public ListViewAdapter(List<View> views) {
		this.views=views;
	}

	@Override
	public Object getItem(int position) {
		return views.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return (View) getItem(position);
	}

}
