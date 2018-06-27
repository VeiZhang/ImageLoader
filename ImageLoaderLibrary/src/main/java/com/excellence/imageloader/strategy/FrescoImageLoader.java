package com.excellence.imageloader.strategy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.ImageLoaderOptions;
import com.excellence.imageloader.listener.IListener;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.File;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/27
 *     desc   : Fresco图片加载器
 *     			https://github.com/facebook/fresco
 * </pre> 
 */
public final class FrescoImageLoader implements ImageLoader
{
	private ImageLoaderOptions mOptions = null;

	public FrescoImageLoader(Context context)
	{
		this(context, null);
	}

	public FrescoImageLoader(Context context, ImageLoaderOptions options)
	{
		mOptions = options;
		if (mOptions == null)
		{
			mOptions = new ImageLoaderOptions.Builder().build();
		}

		Fresco.initialize(context);
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, IListener listener)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, int placeholderResId, int errorResId)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, int placeholderResId, int errorResId, IListener listener)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, IListener listener)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, int placeholderResId, int errorResId)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, int placeholderResId, int errorResId, IListener listener)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, IListener listener)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, int placeholderResId, int errorResId)
	{

	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, int placeholderResId, int errorResId, IListener listener)
	{

	}

	@Override
	public void clearCache()
	{

	}
}
