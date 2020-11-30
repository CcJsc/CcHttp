package com.tools.cchttp.progress;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author AlienChao
 * @date 2019/12/31 15:49
 */
public interface ProgressInterceptor {
    Response intercept(Chain chain) throws IOException;

    interface Chain {
        Request request();
        Response proceed(Request request) throws IOException;
        Connection connection();
    }
}
