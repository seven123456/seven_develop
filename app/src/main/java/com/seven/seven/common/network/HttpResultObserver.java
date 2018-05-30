package com.seven.seven.common.network;

import com.seven.seven.common.event.ReloginEvent;
import com.seven.seven.common.utils.Constans;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class HttpResultObserver<T extends ResponseCustom> extends HttpCommonObserver<T> {

    protected abstract void onLoading(Disposable d);

    protected abstract void onSuccess(T o);

    protected abstract void onFail(ApiException e);

    @Override
    protected void onStart(Disposable d) {
        onLoading(d);
    }

    @Override
    protected void _onNext(T responseCustom) {
        if (responseCustom.getErrorCode() < 0) {
            if (responseCustom.getErrorMsg().equals("请登录!")) {
                EventBus.getDefault().post(new ReloginEvent(Constans.RELOGIN));
            } else {
                onFail(new ApiException(responseCustom.getErrorCode(), responseCustom.getErrorMsg()));
            }
        } else {
            onSuccess(responseCustom);
        }
    }

    @Override
    protected void _onError(ApiException error) {
        onFail(error);
//        Log.e("网络处理异常", error.getMessage());
    }
}
