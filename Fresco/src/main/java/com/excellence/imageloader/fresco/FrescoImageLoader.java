package com.excellence.imageloader.fresco;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.excellence.imageloader.ImageLoader;
import com.excellence.imageloader.ImageLoaderOptions;
import com.excellence.imageloader.listener.IListener;
import com.excellence.imageloader.progress.ProgressInterceptor;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.ImageInfo;

import java.io.File;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/27
 *     desc   : Fresco图片加载器
 *     			https://github.com/facebook/fresco
 *
 *     			图片类型
 *     			  Type        |          Scheme         |  Sample
 *     		 	http远程图片   |     http://或者https:// |  HttpURLConnection或者OkHttp
 *     			本地文件       |     file://             |  FileInputStream
 *     		Content provider  |     content://          |  ContentResolver
 *     		res目录下的资源 	  |     res://              |  Resources.openRawResource
 *     		asset目录下的资源  |     asset://            |  AssetManager
 *     		Uri中指定图片数据  |  data:mime/type;base64  |  数据类型必须符合rfc2397规定 (仅支持 UTF-8)
 * </pre> 
 */
public final class FrescoImageLoader implements ImageLoader
{
	private static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";

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

		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.addInterceptor(new ProgressInterceptor());
		OkHttpClient okHttpClient = builder.build();
		ImagePipelineConfig imagePipelineConfig = OkHttpImagePipelineConfigFactory.newBuilder(mContext, okHttpClient).build();

		Fresco.initialize(mContext, imagePipelineConfig);

		if (options.isLogEnable)
		{
			FLog.setMinimumLoggingLevel(FLog.DEBUG);
		}
	}

	private void load(@NonNull ImageView view, Object obj, int placeholderResId, int errorResId, final IListener listener)
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

		Uri uri = formatUri(obj);

		// 关闭缓存，由于Fresco没有关闭缓存的设置，因此只能使用该方式
		if (!mOptions.isCache)
		{
			clearCache(uri);
		}

		GenericDraweeHierarchyBuilder hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources());
		// 全局占位图和错误图
		if (mOptions.mPlaceholderResId != 0)
		{
			hierarchyBuilder.setPlaceholderImage(mOptions.mPlaceholderResId);
		}
		if (mOptions.mErrorResId != 0)
		{
			hierarchyBuilder.setFailureImage(mOptions.mErrorResId);
		}

		// 定制的占位图和错误图，同时有全局图时，以定制的为准
		if (placeholderResId != 0)
		{
			hierarchyBuilder.setPlaceholderImage(placeholderResId);
		}
		if (errorResId != 0)
		{
			hierarchyBuilder.setFailureImage(errorResId);
		}

		// 监听加载进度
		final String url = uri.toString();
		final ImageLoaderListener imageLoaderListener = new ImageLoaderListener(url, listener);
		ProgressInterceptor.addListener(url, imageLoaderListener);

		// 拿取标识
		DraweeHolder draweeHolder = (DraweeHolder) view.getTag(R.id.fresco_drawee);
		PipelineDraweeControllerBuilder controllerBuilder = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(mOptions.isFade).setControllerListener(imageLoaderListener);
		DraweeController controller;
		if (draweeHolder == null)
		{
			draweeHolder = DraweeHolder.create(hierarchyBuilder.build(), mContext);
			controller = controllerBuilder.build();
		}
		else
		{
			controller = controllerBuilder.setOldController(draweeHolder.getController()).build();
		}
		// 请求
		draweeHolder.setController(controller);

		// 生命周期
		ViewStateListener viewStateListener = new ViewStateListener(draweeHolder);
		view.addOnAttachStateChangeListener(viewStateListener);

		// 判断是否ImageView已经 attachToWindow
		if (ViewCompat.isAttachedToWindow(view))
		{
			draweeHolder.onAttach();
		}

		// 保证每一个ImageView中只存在一个draweeHolder
		view.setTag(R.id.fresco_drawee, draweeHolder);

		view.setImageDrawable(draweeHolder.getTopLevelDrawable());
	}

	public Uri formatUri(Object obj)
	{
		Uri uri;
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
			if (Pattern.matches(REGEX_URL, (String) obj))
			{
				// url
				uri = Uri.parse((String) obj);
			}
			else
			{
				// file
				uri = Uri.parse("file://" + obj);
			}
		}
		else
		{
			uri = Uri.EMPTY;
		}
		return uri;
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
		Fresco.getImagePipeline().clearCaches();
	}

	public void clearMemoryCaches()
	{
		Fresco.getImagePipeline().clearMemoryCaches();
	}

	public void clearDiskCaches()
	{
		Fresco.getImagePipeline().clearDiskCaches();
	}

	/**
	 * @see #formatUri(Object) 
	 *
	 * @param uri
	 */
	public void clearCache(Uri uri)
	{
		Fresco.getImagePipeline().evictFromCache(uri);
	}

	/**
	 * @see #formatUri(Object)
	 *
	 * @param uri
	 */
	public void clearMemoryCaches(Uri uri)
	{
		Fresco.getImagePipeline().evictFromMemoryCache(uri);
	}

	/**
	 * @see #formatUri(Object)
	 *
	 * @param uri
	 */
	public void clearDiskCaches(Uri uri)
	{
		Fresco.getImagePipeline().evictFromDiskCache(uri);
	}

	private class ViewStateListener implements View.OnAttachStateChangeListener
	{

		private DraweeHolder mDraweeHolder = null;

		public ViewStateListener(DraweeHolder draweeHolder)
		{
			mDraweeHolder = draweeHolder;
		}

		@Override
		public void onViewAttachedToWindow(View v)
		{
			mDraweeHolder.onAttach();
		}

		@Override
		public void onViewDetachedFromWindow(View v)
		{
			mDraweeHolder.onDetach();
		}
	}

	private class ImageLoaderListener extends BaseControllerListener<ImageInfo> implements IListener
	{

		private String mUrl = null;
		private IListener mListener = null;

		public ImageLoaderListener(String url, IListener listener)
		{
			mUrl = url;
			mListener = listener;
		}

		@Override
		public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable)
		{
			super.onFinalImageSet(id, imageInfo, animatable);
			ProgressInterceptor.removeListener(mUrl);
			onSuccess();
		}

		@Override
		public void onFailure(String id, Throwable throwable)
		{
			super.onFailure(id, throwable);
			ProgressInterceptor.removeListener(mUrl);
			onError(throwable);
		}

		@Override
		public void onProgress(long current, long size)
		{
			if (mListener != null)
			{
				mListener.onProgress(current, size);
			}
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
		public void onError(Throwable t)
		{
			if (mListener != null)
			{
				mListener.onError(t);
			}
		}
	}
}
