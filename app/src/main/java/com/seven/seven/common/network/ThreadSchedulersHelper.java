package com.seven.seven.common.network;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public class ThreadSchedulersHelper {
    /* 上游处理网络请求用io线程（发布事件）
    * 线程上下游切换，上游执行完以后里面切换为主线程（接收事件）
    * */
    public static <T> ObservableTransformer<T, T> rxSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
