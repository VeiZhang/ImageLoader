package com.excellence.imageloader.picasso;

import com.excellence.imageloader.ImageLoaderOptions;

import android.support.annotation.DrawableRes;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/10/10
 *     desc   : Picasso图片加载器参数
 * </pre> 
 */
public class PicassoImageLoaderOptions extends ImageLoaderOptions
{

	private PicassoImageLoaderOptions(Builder builder)
	{
		mPlaceholderResId = builder.mPlaceholderResId;
		mErrorResId = builder.mErrorResId;
		isLogEnable = builder.isLogEnable;
		isFade = builder.isFade;
		isCache = builder.isCache;
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
		/**
		 * 开启打印
		 */
		private boolean isLogEnable = false;
		/**
		 * 是否渐变平滑显示图片
		 */
		private boolean isFade = true;
		/**
		 * 是否使用缓存
		 */
		private boolean isCache = true;

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

		public Builder isLogEnable(boolean isLogEnable)
		{
			this.isLogEnable = isLogEnable;
			return this;
		}

		public Builder isFade(boolean isFade)
		{
			this.isFade = isFade;
			return this;
		}

		public Builder isCache(boolean isCache)
		{
			this.isCache = isCache;
			return this;
		}

		public PicassoImageLoaderOptions build()
		{
			return new PicassoImageLoaderOptions(this);
		}
	}

}
