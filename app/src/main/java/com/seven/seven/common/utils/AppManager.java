package com.seven.seven.common.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by: Seven
 * atuthor:2018/1/4
 * email:seven2016s@163.com
 * 管理activity栈，栈就像子弹上膛
 */

public class AppManager {
    private static AppManager instance;
    private static Stack<Activity> activityStack;

    public AppManager() {
    }

    /*
    * 单一实例
    * */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /*
    *add activity到堆栈
    * */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /*
    * 获取当前activity (堆栈中最后一个进入的)
    * */
    public Activity getCurrentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /*
    * 销毁指定类名的activity
    * */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                //如果栈中的某一个activity和传进来的activity一样时，移除栈里面的activity，而不是传进来的cls
                finishActivity(activity);
            }
        }
    }

    /*
    * 销毁指定的activity
    * */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

}