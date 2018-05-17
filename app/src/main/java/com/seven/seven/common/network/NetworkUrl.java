package com.seven.seven.common.network;

import com.seven.seven.ui.MyApplication;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 */

public class NetworkUrl {
    public static final String ANDROID_TEST_SERVICE ="http://www.wanandroid.com/";
    public static final String ANDROID_BAIDU_SERVICE ="http://www.baidu.com/";

    public static String getNetWorkName(){
        if (MyApplication.getIsDebug()){
            return ANDROID_TEST_SERVICE;
        }else {
            return ANDROID_BAIDU_SERVICE;
        }
    }
}
