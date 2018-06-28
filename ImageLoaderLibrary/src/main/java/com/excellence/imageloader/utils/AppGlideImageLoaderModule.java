package com.excellence.imageloader.utils;

import android.view.View;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/28
 *     desc   : Glide官方推荐的方式
 * </pre> 
 */
@GlideModule
public class AppGlideImageLoaderModule extends AppGlideModule
{
	/**
	 * 使用该方式，才能使用官方推荐的方式 {@link GlideApp#with(View)}；否则Glide写法不一样
	 */
}
