package com.seven.seven.common.network;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author seven
 * @date 2017/10/31
 */

public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        //打印具体错误
        Log.d("HttpResultFunction:", throwable.getMessage());
        return Observable.error(throwable);
    }
}
