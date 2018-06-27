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

	private RequestCreator load(Object obj)
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

		if (mOptions.mPlaceholderResId != 0)
		{
			requestCreator.placeholder(mOptions.mPlaceholderResId);
		}
		if (mOptions.mErrorResId != 0)
		{
			requestCreator.error(mOptions.mErrorResId);
		}
		return requestCreator;
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId)
	{
		load(resId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, int placeholderResId, int errorResId)
	{
		load(resId).placeholder(placeholderResId).error(errorResId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file)
	{
		load(file).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, int placeholderResId, int errorResId)
	{
		load(file).placeholder(placeholderResId).error(errorResId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url)
	{
		load(url).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, int placeholderResId, int errorResId)
	{
		load(url).placeholder(placeholderResId).error(errorResId).into(view);
	}

}
