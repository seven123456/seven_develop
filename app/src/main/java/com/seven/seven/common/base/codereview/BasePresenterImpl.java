package com.seven.seven.common.base.codereview;

import com.seven.seven.common.network.ApiRetrofit;
import com.seven.seven.common.network.ApiService;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by seven
 * on 2018/5/17
 * email:seven2016s@163.com
 */

public class BasePresenterImpl<V extends BaseView, T extends BaseActivity> implements BasePresenter<V, T> {
    public final ApiService apiRetrofit;
    private Reference<V> viewReference;
    private Reference<T> activityReference;
    private V mView;
    private T mActivity;

    public BasePresenterImpl(V view, T activity) {
        attachActivity(activity);
        attachView(view);
        apiRetrofit = ApiRetrofit.getApiRetrofit().getApiServis();
    }

    @Override
    public void attachView(V view) {
        viewReference = new WeakReference<V>(view);
        mView = viewReference.get();
    }

    @Override
    public void attachActivity(T activity) {
        activityReference = new WeakReference<T>(activity);
        mActivity = activityReference.get();
    }

    @Override
    public void detachView() {
        if (isViewAttach()) {
            viewReference.clear();
            viewReference = null;
        }
    }

    @Override
    public void detachActivity() {
        if (isActivityAttach()) {
            activityReference.clear();
            activityReference = null;
        }
    }

    /*
    * view 是否关联
    * */
    public boolean isViewAttach() {
        return viewReference != null && viewReference.get() != null;
    }

    /*
    * activity是否关联
    * */
    public boolean isActivityAttach() {
        return activityReference != null && activityReference.get() != null;
    }

    /*
    * 获取view
    * 
    * */
    public V getView() {
        return viewReference == null ? null : viewReference.get();
    }

    /*
    * 获取activity
    * */
    public T getActivity() {
        return activityReference == null ? null : activityReference.get();
    }
}
