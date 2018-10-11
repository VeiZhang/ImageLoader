package com.excellence.imageloader.progress;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.excellence.imageloader.listener.IListener;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/10/11
 *     desc   : 拦截器监听进度 https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/Progress.java
 * </pre> 
 */
public final class ProgressInterceptor implements Interceptor
{
	static final Map<String, IListener> LISTENER_LIST = new HashMap<>();

	public static void addListener(String url, IListener listener)
	{
		LISTENER_LIST.put(url, listener);
	}

	public static void removeListener(String url)
	{
		LISTENER_LIST.remove(url);
	}

	@Override
	public Response intercept(Chain chain) throws IOException
	{
		Request request = chain.request();
		Response response = chain.proceed(request);
		String url = request.url().toString();
		ResponseBody body = response.body();
		Response newResponse = response.newBuilder().body(new ProgressResponseBody(url, body)).build();
		return newResponse;
	}

}
