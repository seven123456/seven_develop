package com.seven.seven.mvp.presenter;

import android.support.annotation.NonNull;

import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.mvp.model.TestInfo;
import com.seven.seven.mvp.model.TestModle;
import com.seven.seven.mvp.view.TestActivity3;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018-03-15.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestPresenter extends TestContract.TestPresenter {

    private boolean isSuccess = true;
//    @NonNull
//    public  TestPresenter newInstance(TestContract.ITestView mTestView) {
////        return new TestPresenter();
//    }

    public TestPresenter(TestContract.ITestView mTestView) {
        mView = mTestView;
        mModle = TestModle.newInstance();
    }

    @Override
    public void onStart() {

    }

    /*
    *
    * 拿到m层对象,然后请求网络
    * *//*
    @Override
    public TestContract.ITestModle getModel() {
        return mModle;
    }
*/
    /*
    * 展示数据
    * isSuccess 伪代码
    * */
    @Override
    public void loadTestList() {//处理数据，完成并返回给view展示
//        if (isSuccess) {
//            mView.showTestData("v层拿到m层数据去展示");
//        }
        TestInfo testInfo = mModle.getTestInfo("a", "a", "a");
        List<TestInfo> testInfos = new ArrayList<>();
        testInfos.add(testInfo);
        mView.showTestData(testInfos);
//        mView.showToast("v层拿到m层数据去展示");

    }
}
