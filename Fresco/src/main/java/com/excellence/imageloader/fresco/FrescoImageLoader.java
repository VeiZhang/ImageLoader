package com.excellence.imageloader.fresco;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.ImageLoaderOptions;
import com.excellence.imageloader.listener.IListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/27
 *     desc   : Fresco图片加载器
 *     			https://github.com/facebook/fresco
 * </pre> 
 */
public final class FrescoImageLoader implements ImageLoader
{
	private Context mContext = null;
	private ImageLoaderOptions mOptions = null;

	public FrescoImageLoader(Context context)
	{
		this(context, null);
	}

	public FrescoImageLoader(Context context, ImageLoaderOptions options)
	{
		mContext = context.getApplicationContext();
		mOptions = options;
		if (mOptions == null)
		{
			mOptions = new ImageLoaderOptions.Builder().build();
		}

		Fresco.initialize(mContext);
	}

	private void load(@NonNull ImageView view, Object obj, int placeholderResId, int errorResId, IListener listener)
	{
		ViewGroup.LayoutParams params = view.getLayoutParams();
		if (params.width == WRAP_CONTENT)
		{
			params.width = MATCH_PARENT;
		}
		if (params.height == WRAP_CONTENT)
		{
			params.height = MATCH_PARENT;
		}
		view.setLayoutParams(params);

		Uri uri = null;
		if (obj instanceof Integer)
		{
			uri = Uri.parse("res://com.excellence.imageloader/" + obj);
		}
		else if (obj instanceof File)
		{
			uri = Uri.parse("file://" + ((File) obj).getPath());
		}
		else if (obj instanceof String)
		{
			uri = Uri.parse((String) obj);
		}
		else
		{
			uri = Uri.EMPTY;
		}

		if (view instanceof SimpleDraweeView)
		{
			view.setImageURI(uri);
		}
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
		load(view, resId, placeholderResId, errorResId, listener);
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
		load(view, file, placeholderResId, errorResId, listener);
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
		load(view, url, placeholderResId, errorResId, listener);
	}

	@Override
	public void clearCache()
	{

	}
}
