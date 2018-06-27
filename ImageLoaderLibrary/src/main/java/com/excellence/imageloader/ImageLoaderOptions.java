package com.excellence.imageloader;

import android.support.annotation.DrawableRes;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/21
 *     desc   : 设置参数
 * </pre> 
 */
public class ImageLoaderOptions
{

	public int mPlaceholderResId;
    public int mErrorResId;
    public boolean isCrossFade = false;
    public boolean isCache = false;

	private ImageLoaderOptions(Builder builder)
	{
	}

	public static class Builder
	{
		// 占位图片
		private int mPlaceholderResId;
		// 错误图片
		private int mErrorResId;
		// 是否渐变平滑显示图片
		private boolean isCrossFade = false;
		// 是否使用缓存
		private boolean isCache = false;

		public Builder setPlaceholderResId(@DrawableRes int placeholderResId)
		{
			this.mPlaceholderResId = placeholderResId;
			return this;
		}

		public Builder setErrorResId(@DrawableRes int errorResId)
		{
			this.mErrorResId = errorResId;
			return this;
		}

		public Builder isCrossFade(boolean isCrossFade)
		{
			this.isCrossFade = isCrossFade;
			return this;
		}

		public Builder isCache(boolean isCache)
		{
			this.isCache = isCache;
			return this;
		}

		public ImageLoaderOptions build()
		{
			return new ImageLoaderOptions(this);
		}
	}
}
