package com.seven.seven.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.seven.seven.R;
import com.seven.seven.common.utils.ToastUtils;
import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.mvp.model.TestInfo;
import com.seven.seven.mvp.presenter.TestPresenter;
import com.seven.seven.ui.base.BaseMvpActivity;
import com.seven.seven.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestActivity3 extends BaseMvpActivity<TestContract.TestPresenter, TestContract.ITestModle> implements TestContract.ITestView {

    private TestPresenter mPresenter;

    @NonNull
    @Override
    public BasePresenter initPresenter() {//在这里发起
        mPresenter = new TestPresenter(this);
        return mPresenter;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPresenter.loadTestList();//v给p层发送业务处理，举个例子
        List<String> list2 = new ArrayList<String>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        for (String temp : list2) {
            if ("3".equals(temp)) {
                list2.remove(temp);
                break;
            }
        }
        Log.d("TestActivity3=======",list2.size()+"个");
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.layout_b_fragment;
    }

    @Override
    public void showTestData(List<TestInfo> testInfoList) {
        for (int i = 0; i < testInfoList.size(); i++) {
            ToastUtils.showToast(testInfoList.get(i).a);
        }
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

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
        ToastUtils.showToast(msg);
    }
}
