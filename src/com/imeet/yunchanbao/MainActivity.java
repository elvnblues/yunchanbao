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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.imeet.yunchanbao.adapter.NavigationAdapter;
import com.imeet.yunchanbao.constrant.Const;
import com.imeet.yunchanbao.imgscroll.MyImgScroll;

public class MainActivity extends Activity {
	// UI
	private TextView title_top_text;
	private ImageView title_top_right;

	// ADScroll
	private MyImgScroll msADPager; // ͼƬ����
	private LinearLayout ovalLayout; // Բ������
	private List<View> listViews; // ͼƬ��

	//�����˵�
	private GridView gv_navigation;

	// DATA
	

	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.activity_main);

		init();
		setListener();
	}

	private void init() {
		title_top_text = (TextView) findViewById(R.id.title_top_text);
		title_top_text.setText(R.string.activity_home);

		msADPager = (MyImgScroll) findViewById(R.id.ms_ad_pager);
		ovalLayout = (LinearLayout) findViewById(R.id.ll_vb);
		
		gv_navigation = (GridView)findViewById(R.id.gv_navigation);
		
		InitViewPager();// ��ʼ��ͼƬ
		// ��ʼ����
		msADPager.start(this, listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);
		//��ʼ��gv_navigation
		gv_navigation.setAdapter(new NavigationAdapter(MainActivity.this,Const.nvPics,Const.nvName));
	}

	private void setListener() {
		gv_navigation.setOnItemClickListener(gv_onItemClickListener);
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
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.a, R.drawable.b,
				R.drawable.c, R.drawable.d, R.drawable.e };
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
			listViews.add(imageView);
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
