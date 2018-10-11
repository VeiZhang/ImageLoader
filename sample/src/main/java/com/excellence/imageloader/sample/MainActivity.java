package com.excellence.imageloader.sample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.ImageLoaderOptions;
import com.excellence.imageloader.fresco.FrescoImageLoader;
import com.excellence.imageloader.listener.IListener;
import com.excellence.imageloader.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
	public static final String TAG = MainActivity.class.getSimpleName();

	private ActivityMainBinding mBinding = null;
	private ImageLoader mImageLoader = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		ImageLoaderOptions options = new ImageLoaderOptions.Builder().isLogEnable(true).isCache(false).build();
		mImageLoader = new FrescoImageLoader(this, options);

		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		initView();
	}

	private void initView()
	{
		ImageView imageView = mBinding.img;
		int success = R.drawable.success;
		int placeholder = R.drawable.placeholder;
		int error = R.drawable.error;
		String url = "https://raw.githubusercontent.com/VeiZhang/veizhang.github.io/master/uploads/tiimor.png";
		String bigUrl = "http://online.sccnn.com/desk/411/apple1440_1005.jpg";

		/**
		 * Picasso加载资源图片
		 */
		// mImageLoader.loadImage(imageView, success, placeholder, error);

		/**
		 * Picasso加载网络图片
		 */
		mImageLoader.loadImage(imageView, bigUrl, placeholder, error, new IListener()
		{
			@Override
			public void onProgress(long current, long size)
			{
				Log.i(TAG, "onProgress " + current + " - " + size);
			}

			@Override
			public void onSuccess()
			{
				Log.i(TAG, "onSuccess");
			}

			@Override
			public void onError(Throwable t)
			{
				Log.i(TAG, "onError");
			}
		});

		/**
		 * Picasso加载本地图片
		 */
		// mImageLoader.loadImage(imageView, new File("/sdcard/icon.jpg"), placeholder, error);
	}
}
