package com.tools.cchttp.api;



import com.tools.cchttp.Blog;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author AlienChao
 * @date 2019/08/05 09:51
 */
public interface LoginService {

//Observable<Response<Blog>> createBlog(@Body Blog blog);


    @GET("blog/{id}") //这里的{id} 表示是一个变量
    Observable<ResponseBody> getBlog(@Path("id") int id);




    @GET("https://www.baidu.com/")
    Observable<ResponseBody> getBaidu();

    @GET("http://api.fir.im/apps/latest/5dd4b1a6b2eb4635f0b37b4e?api_token=e37d8b115132fb987db9bcf916fafcd0")
    Observable<UpdateBean> getVersion();

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String url);

}
