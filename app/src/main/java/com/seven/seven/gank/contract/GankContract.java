package com.seven.seven.gank.contract;

import com.seven.seven.common.base.codereview.BaseView;

/**
 * Created by seven
 * on 2018/6/5
 * email:seven2016s@163.com
 */

public interface GankContract {
    interface View extends BaseView{}
    interface Presenter{
        void getGankList();
    }
}
