package com.seven.seven.home.contract;

import com.seven.seven.common.base.BasePresenter;
import com.seven.seven.common.base.IBaseModel;
import com.seven.seven.common.base.IBaseView;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */

public interface HomeContract {
    abstract class HomePresenter extends BasePresenter<IHomeModle, IHomeView> {
        public abstract void loadBanner();
    }

    interface IHomeModle extends IBaseModel {

    }

    interface IHomeView extends IBaseView{

    }
}
