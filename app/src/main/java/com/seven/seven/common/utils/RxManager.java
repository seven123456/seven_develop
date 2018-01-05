package com.seven.seven.common.utils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-01-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class RxManager {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();//管理订阅者

    //注册订阅
    public void register(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    //取消订阅
    public void unSubscribe() {
        compositeDisposable.dispose();
    }

}
