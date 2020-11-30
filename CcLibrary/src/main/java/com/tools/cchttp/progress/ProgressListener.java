package com.tools.cchttp.progress;

/**
 * @author AlienChao
 * @date 2019/12/31 16:19
 */
public interface ProgressListener {
    void onProgress(int progress, long contentLength);
}
