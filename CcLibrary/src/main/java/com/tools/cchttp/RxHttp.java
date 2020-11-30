package com.tools.cchttp;

import android.util.Log;

import com.tools.cchttp.api.LoginService;
import com.tools.cchttp.log.RequestInterceptor;
import com.tools.cchttp.progress.ProgressListener;
import com.tools.cchttp.progress.ProgressResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 方便调度类
 *
 * @author AlienChao
 * @date 2019/08/05 09:48
 */
public class RxHttp {

    // 生产环境地址
    public static String BaseUrl = "http://58.60.188.25:8060/";


    private Map<String, Retrofit> retrofitMap = new HashMap();
    private Interceptor mInterceptor;
    private ProgressListener mProgressListener;


    public static void setBaseUrl(String baseUrl) {
        BaseUrl = baseUrl;
    }

    public void setProgressListener(ProgressListener progressListener) {
        mProgressListener = progressListener;
    }

    public Interceptor getInterceptor() {
        return mInterceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        mInterceptor = interceptor;
    }


    public static RxHttp getInstance() {
        return RxHttpHolder.mRxHttp;
    }

    public static class RxHttpHolder {
        private final static RxHttp mRxHttp = new RxHttp();
    }


    public LoginService getSysService() {
        return getRetrofit(BaseUrl).create(LoginService.class);
    }

    public static LoginService getDefaultService() {
        return getInstance().getRetrofit(BaseUrl).create(LoginService.class);
    }

    public static <T> T getCustomService(final Class<T> service) {
        return  getInstance().getRetrofit(BaseUrl).create(service);
    }

    LoginService mLoginService;

    public void setLoginService(LoginService loginService) {
        mLoginService = loginService;
    }


    /**
     * 获取ServiceApi
     *
     * @param cache
     * @param <T>
     * @return
     */
    public static <T> T getRetrofit(Class<T> cache) {
        return getInstance().getRetrofit(BaseUrl).create(cache);
    }


    /**
     * 根据BaseUrl得到 Retrofit
     *
     * @param serviceUrl
     * @return
     */
    public Retrofit getRetrofit(String serviceUrl) {
        Retrofit retrofit;
        if (retrofitMap.containsKey(serviceUrl)) {
            retrofit = retrofitMap.get(serviceUrl);
        } else {
            retrofit = createRetrofit(serviceUrl);
            retrofitMap.put(serviceUrl, retrofit);
        }
        return retrofit;
    }


    OkHttpClient mOkHttpClient;

    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            getInstance().getRetrofit(BaseUrl);
        }
        return mOkHttpClient;
    }

    //构建我们的进度监听器
    final ProgressResponseBody.ProgressListener listener = new ProgressResponseBody.ProgressListener() {
        @Override
        public void update(long bytesRead, long contentLength, boolean done) {

            try {
                //计算百分比并更新ProgressBar
                if (null != mProgressListener) {
                    final int percent = (int) (100 * bytesRead / contentLength);
                    Log.e("jsc", "完成没:" + done + "+RxHttp-update-bytesRead:" + bytesRead + "-contentLength:" + contentLength);
                    mProgressListener.onProgress(percent, contentLength);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            Log.d("jsc", "下载进度：" + (100 * bytesRead) / contentLength + "%");
        }
    };

    /**
     * 创建 Retrofit
     *
     * @param baseUrl
     * @return
     */
    private Retrofit createRetrofit(String baseUrl) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .readTimeout(15, TimeUnit.SECONDS)
//                .writeTimeout(15, TimeUnit.SECONDS)
//                //  .addInterceptor(InterceptorUtil.LogInterceptor())
//                .addInterceptor(mInterceptor);//添加其他拦截器


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//默认重试一次，若需要重试N次，则要实现拦截器。
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        //  .addInterceptor(InterceptorUtil.LogInterceptor())
        if (mInterceptor != null) {
            builder.addInterceptor(mInterceptor);
        }

        builder.addInterceptor(new RequestInterceptor());

//        if (BuildConfig.DEBUG) {
//            builder.addInterceptor(new RequestInterceptor());
//        }


        OkHttpClient okHttpClient = builder
                .addNetworkInterceptor(new Interceptor() {
                                           @Override
                                           public Response intercept(Chain chain) throws IOException {
                                               Response response = chain.proceed(chain.request());
                                               //这里将ResponseBody包装成我们的ProgressResponseBody
                                               return response.newBuilder()
                                                       .body(new ProgressResponseBody(response.body(), listener))
                                                       .build();
                                           }
                                       }
                ).build();


        mOkHttpClient = okHttpClient;
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }


}
