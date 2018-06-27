package com.excellence.imageloader.strategy;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.ImageLoaderOptions;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/21
 *     desc   :
 * </pre>
 */
abstract class BaseImageLoader implements ImageLoader
{
	private static BaseImageLoader mInstance = null;
	protected ImageLoaderOptions mOptions = null;

	public static ImageLoader init()
	{
		return init(null);
	}

	public static ImageLoader init(ImageLoaderOptions options)
	{
		if (mInstance == null)
		{
			mInstance = new PicassoImageLoader(options);
		}
		return mInstance;
	}

	BaseImageLoader(ImageLoaderOptions options)
	{
		mOptions = options;
		if (mOptions == null)
		{
			mOptions = new ImageLoaderOptions.Builder().build();
		}
	}
}
