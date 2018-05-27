package com.seven.seven.common.network;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.seven.seven.common.base.HttpErrorReceiver;
import com.seven.seven.common.utils.ToastUtils;

import org.json.JSONException;

import java.net.ConnectException;
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

    protected abstract void onFail(ApiException e);

    @Override
    protected void onStart(Disposable d) {
        onLoading(d);
    }

    @Override
    protected void _onNext(T responseCustom) {
        onSuccess(responseCustom);
    }

    @Override
    protected void _onError(ApiException error) {
        onFail(error);
//        Log.e("网络处理异常", error.getMessage());
    }
}
