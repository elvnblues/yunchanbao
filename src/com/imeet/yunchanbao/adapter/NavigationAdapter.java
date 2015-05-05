package com.imeet.yunchanbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imeet.yunchanbao.R;

public class NavigationAdapter extends BaseAdapter {

	private Context mContext;
	private Integer[] nvPics;
	private Integer[] nvName;

	/**
	 * 
	 * @param content
	 * @param nvPics Í¼Æ¬
	 * @param nvName Ãû³Æ
	 */
	public NavigationAdapter(Context content , Integer[] nvPics,Integer[] nvName) {
		mContext = content;
		this.nvPics = nvPics;
		this.nvName = nvName;
	}

	@Override
	public int getCount() {
		return nvPics.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.gv_navigation_item, null);
			viewHolder.nv_pic = (ImageView) convertView
					.findViewById(R.id.function_view);
			viewHolder.nv_name = (TextView) convertView
					.findViewById(R.id.function_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.nv_pic.setImageResource(nvPics[position]);
		viewHolder.nv_name.setText(nvName[position]);
		return convertView;
	}
	
}
 class ViewHolder{
	 ImageView nv_pic;
	 TextView nv_name;
}