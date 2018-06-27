package com.excellence.imageloader.strategy;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoaderOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/20
 *     desc   : Picasso加载器
 *              https://github.com/square/picasso
 * </pre>
 */
public class PicassoImageLoader extends BaseImageLoader
{
	private Picasso mPicasso = null;

	protected PicassoImageLoader(ImageLoaderOptions options)
	{
		super(options);
		if (mPicasso == null)
		{
			mPicasso = Picasso.get();
		}
	}

	private RequestCreator load(Object obj, int placeholderResId, int errorResId)
	{
		RequestCreator requestCreator = null;
		if (obj instanceof Integer)
		{
			requestCreator = mPicasso.load((Integer) obj);
		}
		else if (obj instanceof File)
		{
			requestCreator = mPicasso.load((File) obj);
		}
		else if (obj instanceof String)
		{
			requestCreator = mPicasso.load((String) obj);
		}
		else
		{
			requestCreator = mPicasso.load(0);
		}

		// 全局占位图和错误图
		if (mOptions.mPlaceholderResId != 0)
		{
			requestCreator.placeholder(mOptions.mPlaceholderResId);
		}
		if (mOptions.mErrorResId != 0)
		{
			requestCreator.error(mOptions.mErrorResId);
		}

		// 定制的占位图和错误图，同时有全局图时，以定制的为准
		if (placeholderResId != 0)
		{
			requestCreator.placeholder(placeholderResId);
		}
		if (errorResId != 0)
		{
			requestCreator.error(errorResId);
		}
		return requestCreator;
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId)
	{
		loadImage(view, resId, 0, 0);
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, int placeholderResId, int errorResId)
	{
		load(resId, placeholderResId, errorResId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file)
	{
		loadImage(view, file, 0, 0);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, int placeholderResId, int errorResId)
	{
		load(file, placeholderResId, errorResId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url)
	{
		loadImage(view, url, 0, 0);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, int placeholderResId, int errorResId)
	{
		load(url, placeholderResId, errorResId).into(view);
	}

}
