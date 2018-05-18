package com.seven.seven.common.base.codereview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seven.seven.R;
import com.seven.seven.common.utils.ToastUtils;

/**
 * Created  on 2018-01-05.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

//    protected abstract void initView(View rootView, Bundle savedInstanceState);

    /*
   * 带图片的toast
   * */
    public void showSuccessToast(String msg) {
        ToastUtils.success(msg);
    }

    /*
    * error的toast
    * */
    public void showErrorToast(String msg) {
        ToastUtils.error(msg);
    }

    protected abstract int getLayoutId();

    protected  void initView(){

    }


    protected abstract void initData();

    protected abstract void setLisenter();

    protected abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }
}
