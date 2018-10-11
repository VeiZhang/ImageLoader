package com.excellence.imageloader.listener;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/6/27
 *     desc   : 图片加载事件
 * </pre> 
 */
public interface IListener
{
	/**
	 * 加载进度
	 *
	 * @param current 当前进度
	 * @param size 总长度
	 */
	void onProgress(long current, long size);

	/**
	 * 图片加载完成
	 */
	void onSuccess();

	/**
	 * 图片加载失败
	 *
	 * @param t
	 */
	void onError(Throwable t);
}
