package com.seven.seven.home.contract;

import com.seven.seven.common.base.codereview.BaseView;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public interface BaseWebviewContract {
    interface Presenter {
        void collectBlog(int id);
    }

    interface View extends BaseView<Object> {
    }
}
