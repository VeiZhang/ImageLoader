package com.excellence.imageloader;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.excellence.imageloader.listener.IListener;

import java.io.File;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/20
 *     desc   : 策略接口
 * </pre>
 */
public interface ImageLoader
{
	/**
	 * 加载资源图片
	 *
	 * @param view
	 * @param resId
	 */
	void loadImage(@NonNull ImageView view, @DrawableRes int resId);

	void loadImage(@NonNull ImageView view, @DrawableRes int resId, IListener listener);

	/**
	 * 加载资源图片，占位图片，错误图片
	 *
	 * @param view
	 * @param resId
	 * @param placeholderResId
	 * @param errorResId
	 */
	void loadImage(@NonNull ImageView view, @DrawableRes int resId, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

	void loadImage(@NonNull ImageView view, @DrawableRes int resId, @DrawableRes int placeholderResId, @DrawableRes int errorResId, IListener listener);

	/**
	 * 加载本地图片
	 *
	 * @param view
	 * @param file
	 */
	void loadImage(@NonNull ImageView view, @NonNull File file);

	void loadImage(@NonNull ImageView view, @NonNull File file, IListener listener);

	/**
	 * 加载本地图片，占位图片，错误图片
	 *
	 * @param view
	 * @param file
	 * @param placeholderResId
	 * @param errorResId
	 */
	void loadImage(@NonNull ImageView view, @NonNull File file, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

	void loadImage(@NonNull ImageView view, @NonNull File file, @DrawableRes int placeholderResId, @DrawableRes int errorResId, IListener listener);

	/**
	 * 加载网络图片
	 *
	 * @param view
	 * @param url
	 */
	void loadImage(@NonNull ImageView view, @NonNull String url);

	void loadImage(@NonNull ImageView view, @NonNull String url, IListener listener);

	/**
	 * 加载网络图片，占位图片，错误图片
	 *
	 * @param view
	 * @param url
	 * @param placeholderResId
	 * @param errorResId
	 */
	void loadImage(@NonNull ImageView view, @NonNull String url, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

	void loadImage(@NonNull ImageView view, @NonNull String url, @DrawableRes int placeholderResId, @DrawableRes int errorResId, IListener listener);

	void clearCache();
}
