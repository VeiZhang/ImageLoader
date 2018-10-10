package com.excellence.imageloader.picasso;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.ImageLoaderOptions;
import com.excellence.imageloader.listener.IListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/20
 *     desc   : Picasso图片加载器
 *              https://github.com/square/picasso
 * </pre>
 */
public final class PicassoImageLoader implements ImageLoader
{
	private ImageLoaderOptions mOptions = null;
	private Picasso mPicasso = null;

	public PicassoImageLoader()
	{
		this(null);
	}

	public PicassoImageLoader(ImageLoaderOptions options)
	{
		mOptions = options;
		if (mOptions == null)
		{
			mOptions = new ImageLoaderOptions.Builder().build();
		}

		mPicasso = Picasso.get();

		// 开启打印
		mPicasso.setLoggingEnabled(options.isLogEnable);
		/**
		 * 开启指示
		 * 蓝色：从内存中获取，性能最佳
		 * 绿色：从本地获取，性能一般
		 * 红色：从网络加载，性能最差
		 */
		mPicasso.setIndicatorsEnabled(options.isLogEnable);
	}

	private RequestCreator load(@NonNull Object obj, int placeholderResId, int errorResId)
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

		if (!mOptions.isFade)
		{
			requestCreator.noFade();
		}

		if (!mOptions.isCache)
		{
			/** 有网的情况下有效；没网的情况下无效，仍然会加载缓存 **/
			// 跳过内存缓存
			requestCreator.memoryPolicy(MemoryPolicy.NO_CACHE);
			// 跳过磁盘缓存
			requestCreator.networkPolicy(NetworkPolicy.NO_CACHE);
		}

		return requestCreator;
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId)
	{
		loadImage(view, resId, null);
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, IListener listener)
	{
		loadImage(view, resId, 0, 0, listener);
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, int placeholderResId, int errorResId)
	{
		loadImage(view, resId, placeholderResId, errorResId, null);
	}

	@Override
	public void loadImage(@NonNull ImageView view, int resId, int placeholderResId, int errorResId, IListener listener)
	{
		load(resId, placeholderResId, errorResId).into(view, new ImageLoaderListener(listener));
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file)
	{
		loadImage(view, file, null);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, IListener listener)
	{
		loadImage(view, file, 0, 0, listener);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, int placeholderResId, int errorResId)
	{
		loadImage(view, file, placeholderResId, errorResId, null);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull File file, int placeholderResId, int errorResId, IListener listener)
	{
		load(file, placeholderResId, errorResId).into(view, new ImageLoaderListener(listener));
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url)
	{
		loadImage(view, url, null);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, IListener listener)
	{
		loadImage(view, url, 0, 0, listener);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, int placeholderResId, int errorResId)
	{
		loadImage(view, url, placeholderResId, errorResId, null);
	}

	@Override
	public void loadImage(@NonNull ImageView view, @NonNull String url, int placeholderResId, int errorResId, IListener listener)
	{
		load(url, placeholderResId, errorResId).into(view, new ImageLoaderListener(listener));
	}

	@Override
	public void clearCache()
	{
		/**
		 * 清除不了所有的缓存，使用推荐的API
		 * @see Picasso#invalidate(Uri)  {@link #clearCache(Uri)}
		 * @see Picasso#invalidate(File)  {@link #clearCache(File)}
		 * @see Picasso#invalidate(String)  {@link #clearCache(String)}
		 */
	}

	/***************** 定制API *****************/
	public void clearCache(Uri uri)
	{
		mPicasso.invalidate(uri);
	}

	public void clearCache(File file)
	{
		mPicasso.invalidate(file);
	}

	public void clearCache(String path)
	{
		mPicasso.invalidate(path);
	}

	private class ImageLoaderListener implements Callback
	{
		private IListener mListener = null;

		public ImageLoaderListener(IListener listener)
		{
			mListener = listener;
		}

		@Override
		public void onSuccess()
		{
			if (mListener != null)
			{
				mListener.onSuccess();
			}
		}

		@Override
		public void onError(Exception e)
		{
			if (mListener != null)
			{
				mListener.onError();
			}
		}
	}
}
