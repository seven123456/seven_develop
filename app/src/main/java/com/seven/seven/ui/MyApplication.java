package com.seven.seven.ui;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.mob.MobSDK;
import com.seven.seven.common.network.ApiRetrofit;
import com.seven.seven.common.utils.RxTool;


/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public class MyApplication extends Application {

    private static Context context;
    private static boolean isDebug = true;//true 玩Android flase 百度

    public static boolean getIsDebug() {
        return isDebug;
    }

    public static boolean setIsDebug() {
        return false;
    }

    public MyApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Log.i("aaaa", "onCreate: ");
        RxTool.init(this);
        ApiRetrofit.getApiRetrofit();
        MobSDK.init(this, "262adcdbafad0", "c7ee79a0a5971f6fbd0b20f2af152af2");
    }

    public static Context getContext() {
        return context;
    }
}
