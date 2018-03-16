package com.seven.seven.ui.base;

import com.seven.seven.common.utils.RxManager;

import io.reactivex.annotations.NonNull;

/**
 * Created  on 2018-01-05.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BasePresenter<M, V> {
    protected M mModle;
    protected V mView;
    private RxManager rxManager = new RxManager();


    //绑定m和V引用的关系
    public void attachMV(@NonNull M m, @NonNull V v) {
        this.mModle = m;
        this.mView = v;
        this.onStart();
    }

    //解绑m和v的关系
    public void detachMV() {
        rxManager.unSubscribe();
        this.mModle = null;
        this.mView = null;
    }

    //m和v绑定以后进行的一些操作，加载数据 刷新ui之类的操作
    public abstract void onStart();

    //返回presenter想持有的model
    public abstract M getModel();
}
