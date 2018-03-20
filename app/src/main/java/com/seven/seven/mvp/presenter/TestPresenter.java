package com.seven.seven.mvp.presenter;

import android.support.annotation.NonNull;

import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.base.HttpResultObserver;
import com.seven.seven.common.utils.ToastUtils;
import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.mvp.model.TestInfo;
import com.seven.seven.mvp.model.TestModle;
import com.seven.seven.mvp.view.TestActivity3;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
       /* TestInfo testInfo = mModle.getTestInfo("a", "a", "a");
        if (testInfo != null) {
            List<TestInfo> testInfos = new ArrayList<>();
            testInfos.add(testInfo);
            mView.showTestData(testInfos);
        }*/
       /* mModle.getTestInfo().subscribe(new Consumer<Infos>() {
            @Override
            public void accept(Infos infos) throws Exception {
                mView.showTestData(infos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showNetworkError();
            }
        });*/
        mModle.getTestInfo().subscribe(new HttpResultObserver<Infos>() {
            @Override
            protected void onLoading(Disposable d) {

            }

            @Override
            protected void onSuccess(Infos o) {
                ToastUtils.showToast(o.getData().toString());
            }

            @Override
            protected void onFail(Throwable e) {
                ToastUtils.showToast(e.getMessage());
            }
        });
//        mView.showToast("v层拿到m层数据去展示");

    }
}
