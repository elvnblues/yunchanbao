package com.imeet.yunchanbao.adapter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imeet.yunchanbao.R;
import com.imeet.yunchanbao.constrant.ConstClass;
import com.imeet.yunchanbao.entity.SaleInfo;

public class MainSaleAdapter extends BaseAdapter {

	private Context context;
	private List<SaleInfo> saleInfoList;

	public MainSaleAdapter(Context context, List<SaleInfo> saleInfoList) {
		this.context = context;
		this.saleInfoList = saleInfoList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return saleInfoList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return saleInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return saleInfoList.get(position).get_ID();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.lv_main_sale_item, null);
			holder.iv_sale_img = (ImageView) convertView
					.findViewById(R.id.iv_sale_img);
			holder.tv_sale_tag = (TextView) convertView
					.findViewById(R.id.tv_sale_tag);
			holder.tv_sale_price = (TextView) convertView
					.findViewById(R.id.tv_sale_price);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_sale_tag.setText(saleInfoList.get(position).getTag());
		holder.tv_sale_price.setText(saleInfoList.get(position).getPrice());
		Bitmap bmp = ConstClass.getURLimage(saleInfoList.get(position).getImgurl());
		holder.iv_sale_img.setImageBitmap(bmp);
		return convertView;
	}

	class ViewHolder {
		TextView tv_sale_tag;
		TextView tv_sale_price;
		ImageView iv_sale_img;
	}

}
