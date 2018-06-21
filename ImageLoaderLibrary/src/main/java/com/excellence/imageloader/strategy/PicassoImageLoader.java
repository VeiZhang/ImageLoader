package com.excellence.imageloader.strategy;

import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/20
 *     desc   : Picasso加载器
 *              https://github.com/square/picasso
 * </pre>
 */
public class PicassoImageLoader implements ImageLoader
{
	@Override
	public void loadImage(ImageView view, int resId)
	{
		Picasso.get().load(resId).into(view);
	}

	@Override
	public void loadImage(ImageView view, int resId, int placeholderResId, int errorResId)
	{
		Picasso.get().load(resId).placeholder(placeholderResId).error(errorResId).into(view);
	}

	@Override
	public void loadImage(ImageView view, String url)
	{
		Picasso.get().load(url).into(view);
	}

	@Override
	public void loadImage(ImageView view, String url, int placeholderResId, int errorResId)
	{
		Picasso.get().load(url).placeholder(placeholderResId).error(errorResId).into(view);
	}
}
