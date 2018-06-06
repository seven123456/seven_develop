package com.seven.seven.login;

import com.seven.seven.common.base.codereview.BaseView;

/**
 * Created  on 2018-05-26.
 * author:seven
 * email:seven2016s@163.com
 */

public interface RegisterContract {
    interface View extends BaseView<Object> {

    }

    interface Presenter {
        void register(String username, String password, String repassword);
    }
}
