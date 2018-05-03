package com.seven.seven.ui;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.seven.seven.common.network.ApiRetrofit;
import com.seven.seven.common.utils.RxTool;


/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public class MyApplication extends Application {

    private static Context context;

    public MyApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Log.i("aaaa", "onCreate: ");
        RxTool.init(this);
        ApiRetrofit.getApiRetrofit();
    }

    public static Context getContext() {
        return context;
    }
}
