package com.imeet.yunchanbao.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imeet.yunchanbao.R;
import com.imeet.yunchanbao.constrant.ConstClass;
import com.imeet.yunchanbao.entity.SaleInfo;
import com.imeet.yunchanbao.tools.LogUtils;
import com.imeet.yunchanbao.tools.RemoteImageHelper;

public class MainSaleAdapter extends BaseAdapter {
	
	private Context context;
	private List<SaleInfo> saleInfoList;
	private RemoteImageHelper lazyImageHelper = new RemoteImageHelper();
	
	public MainSaleAdapter(Context context, List<SaleInfo> saleInfoList) {
		this.context = context;
		this.saleInfoList = saleInfoList;
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
		String img_url = saleInfoList.get(position).getImgurl();
		//For demo purpose, cache is DISABLED here.
		lazyImageHelper.loadImage(holder.iv_sale_img, img_url, false);
		//To enable cache, simply use following code:
		//lazyImageHelper.loadImage(holder.iv_sale_img, saleInfoList.get(position).getImgurl(), true);
		LogUtils.w("1111");
		
//		Bitmap bmp = ConstClass.getURLimage(saleInfoList.get(position).getImgurl());
//		holder.iv_sale_img.setImageBitmap(bmp);
		return convertView;
	}

	class ViewHolder {
		TextView tv_sale_tag;
		TextView tv_sale_price;
		ImageView iv_sale_img;
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

}
