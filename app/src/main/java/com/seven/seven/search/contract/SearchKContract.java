package com.seven.seven.search.contract;

import com.seven.seven.common.base.codereview.BaseView;

/**
 * Created by seven
 * on 2018/6/2
 * email:seven2016s@163.com
 */

public interface SearchKContract {
    public interface View extends BaseView<Object> {
    }

    interface Presenter {
        void searchK(int page, Object k);
    }
}
