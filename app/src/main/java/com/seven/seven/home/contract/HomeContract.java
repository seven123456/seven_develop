package com.seven.seven.home.contract;

import com.seven.seven.common.base.codereview.BasePresenter;
import com.seven.seven.common.base.IBaseModel;
import com.seven.seven.common.base.IBaseView;
import com.seven.seven.common.base.codereview.BaseView;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */

public interface HomeContract {
    interface View extends BaseView<Object> {
//        void showHomeData();
    }

    interface Presenter {
        void getHomeData();

        void getHomeBanner();

        void getMoreHomeData(int curpage);
    }
}
