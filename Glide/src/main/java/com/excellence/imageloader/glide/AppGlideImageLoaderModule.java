package com.excellence.imageloader.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.Glide;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/28
 *     desc   : Glide官方推荐的方式:
 *              生成 https://bumptech.github.io/glide/doc/generatedapi.html
 * </pre> 
 */

/**
 * 使用该方式，才能使用官方推荐的方式 {@link GlideApp#with(View)}；否则Glide写法不一样 {@link Glide#with(View)}
 */

@GlideModule
public class AppGlideImageLoaderModule extends AppGlideModule
{
	/**
	 * 使用okhttp作为Glide加载
	 *
	 * @param context
	 * @param glide
	 * @param registry
	 */
	@Override
	public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry)
	{
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.addInterceptor(new ProgressInterceptor());
		OkHttpClient okHttpClient = builder.build();
		// 把URLConnection替换为OkHttp
		registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
	}
}
