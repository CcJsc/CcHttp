package com.example.cchttp;



import com.example.cchttp.bean.CcBean;
import com.tools.cchttp.reference.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author MingPeng
 * Created on 2019/8/14
 */
public interface ApiService {

    @POST("UploadFileServlet")
    Observable<ResponseBody> upLoad(@Query("type") String type,
                                    @Query("uploadType") String uploadType,
                                    @Query("staffName") String staffName,
                                    @Body RequestBody Body);

    @GET("https://www.baidu.com/")
    Observable<ResponseBody> getBaidu();



    @GET("https://wanandroid.com/wxarticle/chapters/json")
    Observable<BaseResponse<CcBean>> getOpenApi();
}
