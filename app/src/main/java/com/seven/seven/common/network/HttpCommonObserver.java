package com.seven.seven.common.network;

import android.util.Log;

import java.net.UnknownServiceException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class HttpCommonObserver<T> implements Observer<T> {
    private boolean flag = false;//是否切断上下游的事件传递
    public static final int UN_KNOWN_ERROR = 1000;//未知错误

    public HttpCommonObserver() {
    }

    public HttpCommonObserver(boolean isLink) {
        this.flag = isLink;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.onStart(d);
        if (flag) {
            RxLifeManager.getRxLifeManager().setCompositeDisposableOnStop(d);
        } else {
            RxLifeManager.getRxLifeManager().setCompositeDisposableOnDestroy(d);
        }
    }

    protected abstract void onStart(Disposable d);

    protected abstract void _onNext(T t);

    //    protected abstract void _onError(ApiException e);
    protected abstract void _onError(ApiException e);

    /*
    * t==response
    * */
    @Override
    public void onNext(T t) {
        if (t != null) {
            this._onNext(t);
        }

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException || e instanceof UnknownServiceException) {
            _onError((ApiException) e);
            Log.e("onerror", "错误编码===" + ((ApiException) e).getCode() + "错误信息===" + ((ApiException) e).getMsg());
        } else {
            this._onError(new ApiException(e, UN_KNOWN_ERROR));
        }
//        this._onError(e);
    }


    @Override
    public void onComplete() {

    }
}
