package com.example.cchttp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cchttp.bean.CcBean;
import com.tools.cchttp.RxHttp;
import com.tools.cchttp.reference.BaseObserver;
import com.tools.cchttp.reference.BaseResponse;
import com.tools.cchttp.utils.RxSchedulers;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private TextView tvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 执行http请求
     */
    public <T> Observable<T> exeHttp(Observable<T> observable) {
        return observable.compose(RxSchedulers.observableIO2Main(this));
    }


    private void initHttp() {
//       RxHttp.getInstance().getSysService()
//               .getBaidu()
//               .compose(RxSchedulers.observableIO2Main())
//               .subscribe();

//
//        BaseDialog dialog = new WaitDialog.Builder(context).show();
//


        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();


        RxHttp.getRetrofit(ApiService.class)
                .getOpenApi()
                .compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<CcBean>() {
                    @Override
                    public void onSuccess(CcBean ccBean) {

                    }
                });


        exeHttp(RxHttp.getRetrofit(ApiService.class)
                .getOpenApi())
                .subscribe(new BaseObserver<CcBean>() {
                    @Override
                    public void onSuccess(CcBean ccBean) {

                    }
                });


        RxHttp.getRetrofit(ApiService.class)
                .getOpenApi()
                .compose(RxSchedulers.observableIO2Main())
                .subscribe(new Observer<BaseResponse<CcBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<CcBean> responseBody) {
                        //  Log.e("jsc", "MainActivity-onNext:" + responseBody.getData().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("jsc", "MainActivity-onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        exeHttp(RxHttp.getCustomService(ApiService.class).getOpenApi()).subscribe(new Observer<CcBean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(CcBean responseBody) {
//
//
//                Log.e("jsc", "MainActivity-onNext:" + responseBody.getData().size());
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e("jsc", "MainActivity-onError:" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


    }

    private void initView() {
        tvBtn = (TextView) findViewById(R.id.tv_btn);
        tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initHttp();
            }
        });
    }
}