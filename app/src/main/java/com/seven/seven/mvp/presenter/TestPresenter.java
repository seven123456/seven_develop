package com.seven.seven.mvp.presenter;

import android.support.annotation.NonNull;

import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.mvp.model.TestModle;
import com.seven.seven.mvp.view.TestActivity3;

/**
 * Created  on 2018-03-15.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestPresenter extends TestContract.TestPresenter {

    private boolean isSuccess =true;
    @NonNull
    public static TestPresenter newInstance() {
        return new TestPresenter();
    }

    @Override
    public void onStart() {

    }

    /*
    *
    * 拿到m层对象,然后请求网络
    * */
    @Override
    public TestContract.ITestModle getModel() {
        return TestModle.newInstance();
    }

    /*
    * 展示数据
    * isSuccess 伪代码
    * */
    @Override
    public void loadTestList() {
        if (isSuccess) {
//            mView.showTestData("v层拿到m层数据去展示");
        }
    }
}
