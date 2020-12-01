# CcHttp
 implementation 'com.github.CcJsc:CcHttp:3.4'
 
``` 
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```


### CcHttp 项目介绍

> 介绍： 集成OKHttp2 + Rxjava2  + Retrofit2  的形式拓展。 

一般调用：

```java
        RxHttp.getRetrofit(ApiService.class)
                .getOpenApi()
                .compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<CcBean>() {
                    @Override
                    public void onSuccess(CcBean ccBean) {

                    }
                });

```

Base抽取：

```
         exeHttp(RxHttp.getRetrofit(ApiService.class)
                .getOpenApi())
                .subscribe(new BaseObserver<CcBean>() {
                    @Override
                    public void onSuccess(CcBean ccBean) {

                    }
                });

```


>  其中公共返回格式(BaseResponse)，和 公共解析格式类(BaseObserver) 可以根据自己的项目要求封装





