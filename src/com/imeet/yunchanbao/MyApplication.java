package com.imeet.yunchanbao;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		//����Ĭ�ϵ�ImageLoader���ò���
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(this);
	
		/*	
		//����Ĭ�ϵ�ImageLoader���ò���
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
		.writeDebugLogs() //��ӡlog��Ϣ
		.build();
		*/	
		
		//Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(configuration);

	}

}
