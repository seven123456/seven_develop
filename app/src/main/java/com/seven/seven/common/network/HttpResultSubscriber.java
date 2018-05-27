package com.seven.seven.common.network;


import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;

import static com.seven.seven.common.network.HttpCommonObserver.UN_KNOWN_ERROR;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class HttpResultSubscriber<T> implements Subscriber<T> {
    protected abstract void onLoading(Disposable d);

    protected abstract void onSuccess(T o);

    protected abstract void onFail(ApiException e);


    @Override
    public void onNext(T tResponseCustom) {
        onSuccess(tResponseCustom);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onFail((ApiException) e);
            Log.e("onerror", "错误编码===" + ((ApiException) e).getCode() + "错误信息===" + ((ApiException) e).getMsg());
        } else {
            this.onFail(new ApiException(e, UN_KNOWN_ERROR));
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }
}
