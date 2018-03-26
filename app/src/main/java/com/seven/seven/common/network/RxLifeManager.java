package com.seven.seven.common.network;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-20.
 * author:seven
 * email:seven2016s@163.com
 * <p>
 * rxlifemanager用于切断上游与下游直接的联系，
 * 也就是说我只要执行了disposable,上游事件将无法传递给下游去接受 订阅关系结束
 * 集中处理在baseactivity里面就足够了，如果某一个页面需要就单独处理
 * 有没有效阻止rx泄露我也不知道 ，在这里统一做disposbale是为了封装observer处理onstart()
 */
public class RxLifeManager {

    private static volatile RxLifeManager rxLifeManager;
    private CompositeDisposable compositeDisposableOnStop = new CompositeDisposable();//在页面onstop的时候切断
    private CompositeDisposable compositeDisposableOnDestroy = new CompositeDisposable();//在页面ondestroy时候切断

    public RxLifeManager() {
    }

    public static RxLifeManager getRxLifeManager() {
        if (rxLifeManager == null) {
            synchronized (RxLifeManager.class) {
                if (rxLifeManager == null) {
                    rxLifeManager = new RxLifeManager();
                }
            }
        }
        return rxLifeManager;
    }

    public void setCompositeDisposableOnStop(@NonNull Disposable s) {
        if (compositeDisposableOnStop != null) {
            compositeDisposableOnStop.add(s);
        } else {
            compositeDisposableOnStop = new CompositeDisposable();
            compositeDisposableOnStop.add(s);
        }
    }

    public void setCompositeDisposableOnDestroy(Disposable s) {
        if (compositeDisposableOnDestroy != null) {
            compositeDisposableOnDestroy.add(s);
        } else {
            compositeDisposableOnDestroy = new CompositeDisposable();
            compositeDisposableOnDestroy.add(s);
        }
    }

    public void onStopDisposable() {
        if (compositeDisposableOnStop != null) {
            compositeDisposableOnStop.dispose();
            compositeDisposableOnStop.clear();
            compositeDisposableOnStop = null;
        }
    }

    public void onDestroy() {
        if (compositeDisposableOnDestroy != null) {
            compositeDisposableOnDestroy.dispose();
            compositeDisposableOnDestroy.clear();
            compositeDisposableOnDestroy = null;
        }
    }
}
