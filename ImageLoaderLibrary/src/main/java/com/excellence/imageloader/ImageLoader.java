package com.excellence.imageloader;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

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
	void loadImage(ImageView view, @DrawableRes int resId);

	/**
	 * 加载资源图片，占位图片，错误图片
	 *
	 * @param view
	 * @param resId
	 * @param placeholderResId
	 * @param errorResId
	 */
	void loadImage(ImageView view, @DrawableRes int resId, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

	/**
	 * 加载本地、网络图片
	 *
	 * @param view
	 * @param url
	 */
	void loadImage(ImageView view, String url);

	/**
	 * 加载本地、网络图片，占位图片，错误图片
	 *
	 * @param view
	 * @param url
	 * @param placeholderResId
	 * @param errorResId
	 */
	void loadImage(ImageView view, String url, @DrawableRes int placeholderResId, @DrawableRes int errorResId);

}
