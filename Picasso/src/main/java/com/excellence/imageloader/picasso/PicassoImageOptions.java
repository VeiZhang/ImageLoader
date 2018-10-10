package com.excellence.imageloader.picasso;

import com.excellence.imageloader.ImageOptions;

import android.support.annotation.DrawableRes;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/10/10
 *     desc   : Picasso图片参数
 * </pre> 
 */
public class PicassoImageOptions extends ImageOptions
{

	private PicassoImageOptions(Builder builder)
	{
		mPlaceholderResId = builder.mPlaceholderResId;
		mErrorResId = builder.mErrorResId;
	}

	public static class Builder
	{
		/**
		 * 占位图片
		 */
		private int mPlaceholderResId;
		/**
		 * 错误图片
		 */
		private int mErrorResId;

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

		public PicassoImageOptions build()
		{
			return new PicassoImageOptions(this);
		}
	}
}
