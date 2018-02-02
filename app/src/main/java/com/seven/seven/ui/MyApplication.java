package com.seven.seven.ui;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.jpush.im.android.api.JMessageClient;

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
        JMessageClient.init(context,true);
        JMessageClient.setDebugMode(true);
        Log.i("aaaa", "onCreate: ");
    }

    public static Context getContext() {
        return context;
    }
}
