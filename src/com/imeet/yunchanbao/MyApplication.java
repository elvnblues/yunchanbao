package com.imeet.yunchanbao;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		//创建默认的ImageLoader配置参数
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(this);
	
		/*	
		//创建默认的ImageLoader配置参数
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
		.writeDebugLogs() //打印log信息
		.build();
		*/	
		
		//Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(configuration);

	}

}
