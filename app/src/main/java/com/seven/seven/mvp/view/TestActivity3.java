package com.seven.seven.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.seven.seven.R;
import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.mvp.model.TestInfo;
import com.seven.seven.mvp.presenter.TestPresenter;
import com.seven.seven.ui.base.BaseMvpActivity;
import com.seven.seven.ui.base.BasePresenter;

import java.util.List;

/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestActivity3 extends BaseMvpActivity<TestContract.TestPresenter, TestContract.ITestModle> implements TestContract.ITestView {

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return TestPresenter.newInstance();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.layout_b_fragment;
    }

    @Override
    public void showTestData(List<TestInfo> testInfoList) {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNoMoreData() {

    }
}
