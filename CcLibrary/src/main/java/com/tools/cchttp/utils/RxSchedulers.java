package com.tools.cchttp.utils;

import android.content.Context;


import com.tools.cchttp.dialog.IHttpDialog;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.components.RxActivity;
import com.trello.rxlifecycle3.components.RxFragment;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragmentActivity;


import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程切换
 */
public class RxSchedulers {

    /**
     * 带有dialog的异步流程
     *
     * @param dialog
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> loadingDialog(final IHttpDialog dialog) {
        //  dialog.show();
//        return upstream -> upstream.doOnTerminate(() -> {
//            dialog.dismiss();
//        });

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        dialog.dismiss();
                    }
                });
                return observable;
            }
        };
    }

    /**
     * 带有dialog的异步流程
     *
     * @param dialog
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> loadingDialog(final Context context, final IHttpDialog dialog) {
        //  dialog.show();
//        return upstream -> upstream.doOnTerminate(() -> {
//            dialog.dismiss();
//        });

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        dialog.dismiss();
                    }
                });
                return composeContext(context, observable);
            }
        };
    }


    public static <T> ObservableTransformer<T, T> observableIO2Main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                return observable;
            }
        };
    }


    public static <T> ObservableTransformer<T, T> observableIO2Main(final Context context) {
        return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                return composeContext(context, observable);
            }
        };
    }


    private static <T> ObservableSource<T> composeContext(Context context, Observable<T> observable) {

        if (context instanceof RxActivity) {
            RxActivity rxActivity = (RxActivity) context;
            return observable.compose(rxActivity.<T>bindUntilEvent(ActivityEvent.DESTROY));
        } else if (context instanceof RxFragmentActivity) {
            return observable.compose(((RxFragmentActivity) context).<T>bindUntilEvent(ActivityEvent.DESTROY));
        } else if (context instanceof RxAppCompatActivity) {
            return observable.compose(((RxAppCompatActivity) context).<T>bindUntilEvent(ActivityEvent.DESTROY));
        } else {
            return observable;
        }

    }


    public static <T> ObservableTransformer<T, T> observableIO2Main(final RxFragment fragment) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).compose(fragment.<T>bindToLifecycle());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> flowableIO2Main() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
