package com.excellence.imageloader.progress;

import java.io.IOException;

import com.excellence.imageloader.listener.IListener;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

import static com.excellence.imageloader.progress.ProgressInterceptor.LISTENER_LIST;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/10/11
 *     desc   : https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/Progress.java
 * </pre>
 */
class ProgressResponseBody extends ResponseBody
{

	private ResponseBody mResponseBody;
	private IListener mListener;
	private BufferedSource mBufferedSource;

	ProgressResponseBody(String url, ResponseBody responseBody)
	{
		mListener = LISTENER_LIST.get(url);
		mResponseBody = responseBody;
	}

	@Override
	public MediaType contentType()
	{
		return mResponseBody.contentType();
	}

	@Override
	public long contentLength()
	{
		return mResponseBody.contentLength();
	}

	@Override
	public BufferedSource source()
	{
		if (mBufferedSource == null)
		{
			mBufferedSource = Okio.buffer(source(mResponseBody.source()));
		}
		return mBufferedSource;
	}

	private Source source(Source source)
	{
		return new ForwardingSource(source)
		{
			long totalBytesRead = 0L;

			@Override
			public long read(Buffer sink, long byteCount) throws IOException
			{
				long bytesRead = super.read(sink, byteCount);
				// read() returns the number of bytes read, or -1 if this source is exhausted.
				totalBytesRead += bytesRead != -1 ? bytesRead : 0;

				if (mListener != null)
				{
					mListener.onProgress(totalBytesRead, mResponseBody.contentLength());
				}
				return bytesRead;
			}
		};
	}

}
