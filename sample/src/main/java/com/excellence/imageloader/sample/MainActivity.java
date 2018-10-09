package com.excellence.imageloader.sample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.ImageLoaderOptions;
import com.excellence.imageloader.listener.IListener;
import com.excellence.imageloader.picasso.PicassoImageLoader;
import com.excellence.imageloader.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
	public static final String TAG = MainActivity.class.getSimpleName();

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
		ImageView imageView = mBinding.img;
		int success = R.drawable.success;
		int placeholder = R.drawable.placeholder;
		int error = R.drawable.error;
		String url = "https://raw.githubusercontent.com/VeiZhang/veizhang.github.io/master/uploads/tiimor.png";

		ImageLoaderOptions options = new ImageLoaderOptions.Builder().isLogEnable(true).isCache(false).build();
		ImageLoader imageLoader = new PicassoImageLoader(options);
		/**
		 * Picasso加载资源图片
		 */
		// imageLoader.loadImage(imageView, success, placeholder, error);

		/**
		 * Picasso加载网络图片
		 */
		imageLoader.loadImage(imageView, url, placeholder, error, new IListener()
		{
			@Override
			public void onProgress()
			{
				Log.i(TAG, "onProgress");
			}

			@Override
			public void onSuccess()
			{
				Log.i(TAG, "onSuccess");
			}

			@Override
			public void onError()
			{
				Log.i(TAG, "onError");
			}
		});

		/**
		 * Picasso加载本地图片
		 */
		// imageLoader.loadImage(imageView, new File("/sdcard/icon.jpg"), placeholder, error);
	}
}
