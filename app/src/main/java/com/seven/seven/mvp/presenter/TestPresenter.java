package com.seven.seven.mvp.presenter;

import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.ToastUtils;
import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.mvp.model.TestModle;

import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-15.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestPresenter extends TestContract.TestPresenter {

    public TestPresenter(TestContract.ITestView mTestView) {
        mView = mTestView;
        mModle = TestModle.newInstance();
    }

    @Override
    public void onStart() {
    }

    /*
    * 展示数据
    * */
    @Override
    public void loadTestList() {//处理数据，完成并返回给view展示

        mModle.getTestInfo().subscribe(new HttpResultObserver<ResponseCustom<Infos>>() {
            @Override
            protected void onLoading(Disposable d) {

            }

            @Override
            protected void onSuccess(ResponseCustom<Infos> o) {
                ToastUtils.showToast(o.getData().toString());
            }

            @Override
            protected void onFail(Throwable e) {
                mView.showNetworkError();
                ToastUtils.error(e.getMessage().toString());
            }
        });

    }
}
