package com.excellence.imageloader.strategy;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

	protected PicassoImageLoader()
	{
		if (mPicasso == null)
		{
			mPicasso = Picasso.get();
		}
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId)
	{
		mPicasso.load(resId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, int placeholderResId, int errorResId)
	{
		mPicasso.load(resId).placeholder(placeholderResId).error(errorResId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file)
	{
		mPicasso.load(file).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, int placeholderResId, int errorResId)
	{
		mPicasso.load(file).placeholder(placeholderResId).error(errorResId).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url)
	{
		mPicasso.load(url).into(view);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, int placeholderResId, int errorResId)
	{
		mPicasso.load(url).placeholder(placeholderResId).error(errorResId).into(view);
	}

}
