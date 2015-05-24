package com.imeet.yunchanbao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.imeet.yunchanbao.adapter.IducationAdapter;
import com.imeet.yunchanbao.adapter.MainSaleAdapter;
import com.imeet.yunchanbao.adapter.NavigationAdapter;
import com.imeet.yunchanbao.constrant.Const;
import com.imeet.yunchanbao.entity.IducationInfo;
import com.imeet.yunchanbao.entity.SaleInfo;
import com.imeet.yunchanbao.myview.MyGridView;
import com.imeet.yunchanbao.myview.MyImgScroll;
import com.imeet.yunchanbao.myview.MyListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

public class MainActivity extends Activity {
	// UI
	private TextView title_top_text;
	private ImageView title_top_right;
	
	private LinearLayout ll_body;
	private ScrollView scroll_main;//���������
	
	private MyListView lv_main_sale;//�ר��List
	
	private MyListView lv_main_iducation;//��Ӥ֪ʶList
	
	private TextView tv_main_top;//���ض���

	// ADScroll
	private MyImgScroll msADPager; // ͼƬ����
	private LinearLayout ovalLayout; // Բ������
	private List<View> adListViews; // ͼƬ��

	//�����˵�
	private MyGridView gv_navigation;

	// DATA
	private MainSaleAdapter mainSaleAdapter; 
	private IducationAdapter iducationAdapter;

	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
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
		
		tv_main_top = (TextView)findViewById(R.id.tv_main_top);
		
		ll_body = (LinearLayout)findViewById(R.id.ll_body);
		scroll_main = (ScrollView)findViewById(R.id.scroll_main);
		scroll_main.requestChildFocus(ll_body, null); 
		
		lv_main_sale = (MyListView)findViewById(R.id.lv_main_sale);

		msADPager = (MyImgScroll) findViewById(R.id.ms_ad_pager);
		ovalLayout = (LinearLayout) findViewById(R.id.ll_vb);
		
		gv_navigation = (MyGridView)findViewById(R.id.gv_navigation);
		
		lv_main_iducation = (MyListView)findViewById(R.id.lv_main_iducation);
		
		InitViewPager();// ��ʼ��ͼƬ
		// ��ʼ����
		msADPager.start(this, adListViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);
		//��ʼ��gv_navigation
		gv_navigation.setAdapter(new NavigationAdapter(MainActivity.this,Const.nvPics,Const.nvName));
	}

	private void setListener() {
		gv_navigation.setOnItemClickListener(gv_onItemClickListener);
		title_top_right.setOnClickListener(my_OnClickLister);
		tv_main_top.setOnClickListener(my_OnClickLister);
		lv_main_sale.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, true));
	}
	
	private void setData(){
		List<SaleInfo> saleInfoList = new ArrayList<SaleInfo>();
		SaleInfo saleInfo1 = new SaleInfo();
		saleInfo1.set_ID(1);
		saleInfo1.setImgurl("http://i.mmcdn.cn/simba/img/TB1PCA5HpXXXXbTaXXXSutbFXXX.jpg");
		saleInfo1.setTag("ͯ����Ʒ������");
		saleInfo1.setPrice("��135");
		saleInfoList.add(saleInfo1);
		SaleInfo saleInfo2 = new SaleInfo();
		saleInfo2.set_ID(2);
		saleInfo2.setImgurl("http://gtms01.alicdn.com/tps/i1/TB14C7oHXXXXXbjaXXXJLmnFXXX-880-70.jpg");
		saleInfo2.setTag("ͯ����Ʒ������");
		saleInfo2.setPrice("��190");
		saleInfoList.add(saleInfo2);
		SaleInfo saleInfo3 = new SaleInfo();
		saleInfo3.set_ID(3);
		saleInfo3.setImgurl("http://gtms02.alicdn.com/tps/i2/TB1zXHVHFXXXXXQXFXXnLSnFXXX-880-70.png");
		saleInfo3.setTag("ͯ����Ʒ������");
		saleInfo3.setPrice("��213");
		saleInfoList.add(saleInfo3);
		mainSaleAdapter = new MainSaleAdapter(MainActivity.this, saleInfoList);
		lv_main_sale.setAdapter(mainSaleAdapter);
		
		List<IducationInfo> IducationList = new ArrayList<IducationInfo>();
		IducationInfo info1 = new IducationInfo();
		info1.set_ID(1);
		info1.setTitle("���ڲ�����Ҫע����Щ��ȫ�����");
		IducationList.add(info1);
		IducationInfo info2 = new IducationInfo();
		info2.set_ID(2);
		info2.setTitle("���º�ı���ͻȻ�ܰ�����ô�죿��");
		IducationList.add(info2);
		IducationInfo info3= new IducationInfo();
		info3.set_ID(3);
		info3.setTitle("�ճ�����С����ôϴ�裿��");
		IducationList.add(info3);
		IducationInfo info4 = new IducationInfo();
		info4.set_ID(4);
		info4.setTitle("С��������������ô�죿��");
		IducationList.add(info4);
		iducationAdapter = new IducationAdapter(MainActivity.this,IducationList);
		lv_main_iducation.setAdapter(iducationAdapter);
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
	 * ��ʼ��ADScrollͼƬ
	 */
	private void InitViewPager() {
		adListViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.img_ad1, R.drawable.img_ad2};
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {// ����ͼƬ����¼�
					Toast.makeText(MainActivity.this,
							"�����:" + msADPager.getCurIndex(),
							Toast.LENGTH_SHORT).show();
				}
			});
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			adListViews.add(imageView);
		}
	}
	private OnItemClickListener gv_onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "ѡ�еĹ����ǣ�"+MainActivity.this.getString( Const.nvName[position]),Toast.LENGTH_SHORT).show();  
		}
	};
	private OnClickListener my_OnClickLister = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.tv_main_top:
				//���ض���
				scroll_main.scrollTo(0, 0);
				break;
			case R.id.title_top_right://��������
				Toast.makeText(MainActivity.this, "�����������", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	
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
