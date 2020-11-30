package com.tools.cchttp.progress;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

/**
 * @author AlienChao
 * @date 2019/12/31 15:46
 */
public class ProgressResponseBody extends ResponseBody {
    //回调接口
    public interface ProgressListener {
        /**
         * @param bytesRead     已经读取的字节数
         * @param contentLength 响应总长度
         * @param done          是否读取完毕
         */
        void update(long bytesRead, long contentLength, boolean done);
    }

    private final ResponseBody responseBody;
    private final ProgressListener progressListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    //source方法下面会继续说到.
    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;

    }

    @Override
    public void close() {
        Log.e("jsc", "ProgressResponseBody-close:");
        super.close();
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public Timeout timeout() {
                Log.i("jsc","超时");
                return super.timeout();
            }

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {



                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;   //不断统计当前下载好的数据
                //接口回调
                progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };

    }

}
