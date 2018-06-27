package com.excellence.imageloader.listener;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/27
 *     desc   : 图片加载进度
 * </pre> 
 */
public interface IListener
{
	void onProgress();

	void onSuccess();

	void onError();
}
