package com.seven.seven.common.base;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.seven.seven.common.utils.ToastUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class HttpResultObserver<T> extends HttpCommonObserver<T> {

    protected abstract void onLoading(Disposable d);

    protected abstract void onSuccess(T o);

    protected abstract void onFail(Throwable e);

    @Override
    protected void onStart(Disposable d) {
        onLoading(d);
    }

    @Override
    protected void _onNext(T o) {
        onSuccess(o);
    }

    @Override
    protected void _onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.error("网络连接超时,请检查网络");
        } else if (e instanceof ConnectException) {
            ToastUtils.error("网络连接异常,请检查网络");
        } else if (e instanceof UnknownHostException) {
            ToastUtils.error("服务器异常,请稍后再试");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            ToastUtils.error("数据解析错误");
        }
        onFail(e);
    }

}
