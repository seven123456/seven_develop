package com.seven.seven.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseMvpFragment<P extends BasePresenter, M extends IBaseModel> extends BaseFragment implements IBaseView {
    @Override
    protected void initView() {
        initPresenter();
        super.initView();
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {

    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {

    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void back() {

    }
}
