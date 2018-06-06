package com.seven.seven.common.network.gank;

import com.seven.seven.common.network.gank.GankIoCustom;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by seven
 * on 2018/5/17
 * email:seven2016s@163.com
 */

public class GankIoObservable {
    public static <T> Observable getObservable(Observable<GankIoCustom<T>> customObservable) {
        Observable observable = customObservable
//                .map(new ServerResultFunction())
//                .onErrorResumeNext(new HttpResultFunction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
