package com.seven.seven.mvp.contract;

import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.base.BasePresenter;
import com.seven.seven.common.base.IBaseModel;
import com.seven.seven.common.base.IBaseView;
import com.seven.seven.common.network.ResponseCustom;

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
        io.reactivex.Observable<ResponseCustom<Infos>> getTestInfo();//获取数据
    }

    interface ITestView extends IBaseView {//view层对应的方法
        /*
        * 展示测试数据
        * */

        void showTestData(Infos testInfoList);//

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
