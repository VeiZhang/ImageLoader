package com.excellence.imageloader.strategy;

import com.excellence.imageloader.ImageLoader;

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

	public static ImageLoader init()
	{
		if (mInstance == null)
		{
			mInstance = new PicassoImageLoader();
		}
		return mInstance;
	}

}
