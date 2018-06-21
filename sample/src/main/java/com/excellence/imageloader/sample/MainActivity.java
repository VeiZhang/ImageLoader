package com.excellence.imageloader.sample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.sample.databinding.ActivityMainBinding;
import com.excellence.imageloader.strategy.PicassoImageLoader;

public class MainActivity extends AppCompatActivity
{

	private ActivityMainBinding mBinding = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		ImageLoader imageLoader = new PicassoImageLoader();
		imageLoader.loadImage(mBinding.picassoImg, R.drawable.success, R.drawable.placeholder, R.drawable.error);
	}
}
