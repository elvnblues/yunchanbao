package com.imeet.yunchanbao.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.imeet.yunchanbao.R;
import com.imeet.yunchanbao.entity.IducationInfo;


public class IducationAdapter extends BaseAdapter {
	
	private Context context;
	private List<IducationInfo> iDucationList;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return iDucationList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return iDucationList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return iDucationList.get(position).get_ID();
	}
	public IducationAdapter(Context context, List<IducationInfo> iDucationList){
		this.context = context;
		this.iDucationList = iDucationList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.lv_main_iducation_item, null);
			holder.tv_title = (TextView)convertView.findViewById(R.id.tv_iducation_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_title.setText(iDucationList.get(position).getTitle());
		return convertView;
	}
	class ViewHolder {
		TextView tv_title;
	}
}
