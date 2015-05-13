package com.imeet.yunchanbao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.imeet.yunchanbao.adapter.MainSaleAdapter;
import com.imeet.yunchanbao.adapter.NavigationAdapter;
import com.imeet.yunchanbao.constrant.Const;
import com.imeet.yunchanbao.constrant.ConstClass;
import com.imeet.yunchanbao.entity.SaleInfo;
import com.imeet.yunchanbao.myview.MyGridView;
import com.imeet.yunchanbao.myview.MyImgScroll;
import com.imeet.yunchanbao.myview.MyListView;

public class MainActivity extends Activity {
	// UI
	private TextView title_top_text;
	private ImageView title_top_right;
	
	private LinearLayout ll_body;
	private ScrollView scroll_main;
	
	private MyListView lv_main_sale;
	
	

	// ADScroll
	private MyImgScroll msADPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组

	//导航菜单
	private MyGridView gv_navigation;

	// DATA
	private MainSaleAdapter mainSaleAdapter; 
	

	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除标题栏
		setContentView(R.layout.activity_main);

		init();
		setListener();
		setData();
	}

	private void init() {
		title_top_text = (TextView) findViewById(R.id.title_top_text);
		title_top_text.setText(R.string.activity_home);
		title_top_right = (ImageView)findViewById(R.id.title_top_right);
		title_top_right.setVisibility(View.VISIBLE);
		title_top_right.setBackgroundResource(R.drawable.img_title_userceter);
		
		ll_body = (LinearLayout)findViewById(R.id.ll_body);
		scroll_main = (ScrollView)findViewById(R.id.scroll_main);
		scroll_main.requestChildFocus(ll_body, null); 
		
		lv_main_sale = (MyListView)findViewById(R.id.lv_main_sale);

		msADPager = (MyImgScroll) findViewById(R.id.ms_ad_pager);
		ovalLayout = (LinearLayout) findViewById(R.id.ll_vb);
		
		gv_navigation = (MyGridView)findViewById(R.id.gv_navigation);
		
		InitViewPager();// 初始化图片
		// 开始滚动
		msADPager.start(this, listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);
		//初始化gv_navigation
		gv_navigation.setAdapter(new NavigationAdapter(MainActivity.this,Const.nvPics,Const.nvName));
	}

	private void setListener() {
		gv_navigation.setOnItemClickListener(gv_onItemClickListener);
		title_top_right.setOnClickListener(my_OnClickLister);
	}
	
	private void setData(){
		List<SaleInfo> saleInfoList = new ArrayList<SaleInfo>();
		SaleInfo saleInfo1 = new SaleInfo();
		saleInfo1.set_ID(1);
		saleInfo1.setImgurl("http://i.mmcdn.cn/simba/img/TB1PCA5HpXXXXbTaXXXSutbFXXX.jpg");
		saleInfo1.setTag("童车新品上线啦");
		saleInfo1.setPrice("￥135");
		saleInfoList.add(saleInfo1);
		SaleInfo saleInfo2 = new SaleInfo();
		saleInfo2.set_ID(2);
		saleInfo2.setImgurl("http://gtms01.alicdn.com/tps/i1/TB14C7oHXXXXXbjaXXXJLmnFXXX-880-70.jpg");
		saleInfo2.setTag("童车新品上线啦");
		saleInfo2.setPrice("￥190");
		saleInfoList.add(saleInfo2);
		SaleInfo saleInfo3 = new SaleInfo();
		saleInfo3.set_ID(3);
		saleInfo3.setImgurl("http://gtms02.alicdn.com/tps/i2/TB1zXHVHFXXXXXQXFXXnLSnFXXX-880-70.png");
		saleInfo3.setTag("童车新品上线啦");
		saleInfo3.setPrice("￥213");
		saleInfoList.add(saleInfo3);
		mainSaleAdapter = new MainSaleAdapter(MainActivity.this, saleInfoList);
		lv_main_sale.setAdapter(mainSaleAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 初始化ADScroll图片
	 */
	private void InitViewPager() {
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.a, R.drawable.b,
				R.drawable.c, R.drawable.d, R.drawable.e };
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {// 设置图片点击事件
					Toast.makeText(MainActivity.this,
							"点击了:" + msADPager.getCurIndex(),
							Toast.LENGTH_SHORT).show();
				}
			});
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}
	private OnItemClickListener gv_onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "选中的功能是："+MainActivity.this.getString( Const.nvName[position]),Toast.LENGTH_SHORT).show();  
		}
	};
	private OnClickListener my_OnClickLister = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "进入个人中心", Toast.LENGTH_SHORT).show();
		}
	};

	public void setListViewHeightBasedOnChildren(ListView listView) {

	    //获取listview的适配器
	    ListAdapter listAdapter = listView.getAdapter();
	    //item的高度
	    int itemHeight = 46;

	    if (listAdapter == null) {
	        return;
	    }

	    int totalHeight = 0;

	    for (int i = 0; i < listAdapter.getCount(); i++) {
	    totalHeight += ConstClass.Dp2Px(getApplicationContext(),itemHeight)+listView.getDividerHeight();
	    }

	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight;

	    listView.setLayoutParams(params);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
	}
	
	@Override
	protected void onRestart() {
		msADPager.startTimer();
		super.onRestart();
	}

	@Override
	protected void onStop() {
		msADPager.stopTimer();
		super.onStop();
	}

	public void stop(View v) {
		msADPager.stopTimer();
	}
}
