package com.seven.seven.common.base.codereview;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created  on 2018-05-25.
 * author:seven
 * email:seven2016s@163.com
 */

public class BaseRetryWhen implements Function<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;//请求次数
    private final int retryDelayMillis;//请求间隔时间
    private int retryCount;

    public BaseRetryWhen(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {

        return observable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {

                Log.d("重新请求", "1111");
                if (++retryCount <= maxRetries) {
                    return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                }
                return Observable.error(throwable);
            }
        });
    }
}
