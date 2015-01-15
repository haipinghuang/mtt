package com.loopfire.meitaotao.function.barber.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.util.Util;
/**
 * 美发fragment的gridview item适配器
 * @author Administrator
 *
 */
public class HairdressAdapter extends BaseAdapter{
	private List<String> hairdressNames;
	private List<String> hairdressImages;
	private Context context;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return hairdressImages.size();
	}

	public HairdressAdapter(List<String> hairdressNames,
			List<String> hairdressImages,Context context) {
		super();
		this.hairdressNames = hairdressNames;
		this.hairdressImages = hairdressImages;
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.gridview_hairdress_item, null);
		}
		TextView textview = (TextView) convertView.findViewById(R.id.textView);
		ImageView image=(ImageView) convertView.findViewById(R.id.image);
		int textId=context.getResources().getIdentifier(hairdressNames.get(position), "string", context.getPackageName());
		int imageId=context.getResources().getIdentifier(hairdressImages.get(position), "drawable", context.getPackageName());
//		textview.setText(textId);
		image.setImageResource(imageId);
//		image.setBackgroundResource(imageId);
		return convertView;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
