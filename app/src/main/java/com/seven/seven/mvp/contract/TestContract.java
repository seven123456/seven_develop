package com.seven.seven.mvp.contract;

import android.database.Observable;

import com.seven.seven.mvp.model.TestInfo;
import com.seven.seven.ui.base.BasePresenter;
import com.seven.seven.ui.base.IBaseModel;
import com.seven.seven.ui.base.IBaseView;

import java.io.Serializable;
import java.util.List;

/**
 * Created  on 2018-03-15.
 * author:seven
 * email:seven2016s@163.com
 */

public interface TestContract {
    abstract class TestPresenter extends BasePresenter<ITestModle, ITestView> {
        public abstract void loadTestList();//展示数据，由view去决定展示什么页面或者数据
    }

    interface ITestModle extends IBaseModel {
        Observable<TestInfo> getTestInfo(String a1, String a2, String a3);
    }

    interface ITestView extends IBaseView {
        /*
        * 展示测试数据
        * */

        void showTestData(List<TestInfo> testInfoList);//

        /**
         * 显示网络错误
         */
        void showNetworkError();

        /**
         * 显示加载更多错误
         */
        void showLoadMoreError();

        /**
         * 显示没有更多数据
         */
        void showNoMoreData();
    }
}
