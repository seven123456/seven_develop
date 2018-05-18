package com.seven.seven.common.network;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.seven.seven.common.base.HttpErrorReceiver;

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

    protected abstract void onFail(Throwable e);

    @Override
    protected void onStart(Disposable d) {
        onLoading(d);
    }

    @Override
    protected void _onNext(T responseCustom) {
        onSuccess(responseCustom);
    }

    @Override
    protected void _onError(Throwable error) {
        if (error instanceof SocketTimeoutException) {
//            ToastUtils.error("网络连接超时,请检查网络");
        } else if (error instanceof ConnectException) {
//            ToastUtils.error("网络连接异常,请检查网络");
        } else if (error instanceof UnknownHostException) {
//            ToastUtils.error("服务器异常,请稍后再试");
        } else if (error instanceof JsonParseException
                || error instanceof JSONException
                || error instanceof ParseException || error instanceof MalformedJsonException) {
//            ToastUtils.error("数据解析错误");
           /* Intent intent = new Intent();
            intent.setAction(ConnectivityManager.CONNECTIVITY_ACTION);
            intent.putExtra("msg", "错误消息");
            //发送广播
            sendBroadcast(intent);*/
            /*HttpErrorInfo httpErrorInfo = new HttpErrorInfo("服务器异常,请稍后再试");
            EventBus.getDefault().post(httpErrorInfo);*/
        }
        onFail(error);
        Log.e("网络处理异常", error.getMessage().toString());
    }
}
