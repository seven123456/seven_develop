package com.seven.seven.common.network;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class HttpResultSubscriber<T> implements Subscriber<T> {
    protected abstract void onLoading(Disposable d);

    protected abstract void onSuccess(T o);

    protected abstract void onFail(Throwable e);


    @Override
    public void onNext(T tResponseCustom) {
        onSuccess(tResponseCustom);
    }

    @Override
    public void onError(Throwable t) {
        onFail(t);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }
}
