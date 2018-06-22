package com.excellence.imageloader.sample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.sample.databinding.ActivityMainBinding;
import com.excellence.imageloader.strategy.PicassoImageLoader;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

	private ActivityMainBinding mBinding = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		initView();
	}

	private void initView()
	{
		ImageView imageView = mBinding.picassoImg;
		int success = R.drawable.success;
		int placeholder = R.drawable.placeholder;
		int error = R.drawable.error;
		String url = "https://raw.githubusercontent.com/VeiZhang/veizhang.github.io/master/uploads/tiimor.png";

		ImageLoader imageLoader = PicassoImageLoader.init();
		/**
		 * Picasso加载资源图片
		 */
		// imageLoader.loadImage(imageView, success, placeholder, error);

		/**
		 * Picasso加载网络图片
		 */
		// imageLoader.loadImage(imageView, url, placeholder, error);

		/**
		 * Picasso加载本地图片
		 */
		imageLoader.loadImage(imageView, new File("/sdcard/icon.jpg"), placeholder, error);
	}
}
