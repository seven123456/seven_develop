package com.seven.seven.common.base.codereview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created  on 2018年6月27日 .
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseLazyFragment extends Fragment implements View.OnClickListener {
    public View rootView;
    private boolean isViewCreated;//fragment页面是否加载完
    private boolean isUiVisible;//fragment对用户是否可见
    private boolean isFirst = true;//第一次加载

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        initView();
        setLisenter();
        initData();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /*isVisibleToUser表示fragment的UI用户是否可见*/
        if (getUserVisibleHint()) {
            isUiVisible = true;
            onLazyLoad();
        } else {
            isUiVisible = false;
            onUiGon();
        }
    }


    private void onLazyLoad() {
        if (isUiVisible && isViewCreated && isFirst) {
            onLazyLoadData();
            /*加载完成以后初始化标记，
            * 页面销毁的时候也会初始化
            * isFrist只有在页面销毁的时候才初始化
            * 只加载一次
            * */
            isViewCreated = false;
            isUiVisible = false;
            isFirst = false;
        }
    }

    /*页面可见，页面初始化加载数据*/
    protected abstract void onLazyLoadData();

    /*页面不可见时*/
    protected abstract void onUiGon();

    public void toast(String msg) {
//        ToastUtils.showSingleToast(getContext(), msg);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setLisenter();

    protected abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /*页面销毁的时候把标记初始化*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        isUiVisible = false;
        isFirst = true;
    }
}
