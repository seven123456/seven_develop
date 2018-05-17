package com.seven.seven.common.base.codereview;

/**
 * Created  on 2018-01-05.
 * author:seven
 * email:seven2016s@163.com
 */

public  interface  BasePresenter<V extends BaseView, T extends BaseActivity> {
    void attachView(V view);
    void attachActivity(T activity);
    void detachView();
    void detachActivity();
}
