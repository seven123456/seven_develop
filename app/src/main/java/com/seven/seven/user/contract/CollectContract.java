package com.seven.seven.user.contract;

import com.seven.seven.common.base.codereview.BaseView;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public interface CollectContract {
    interface View extends BaseView<Object>{}
    interface Presenter{
        void getCollectList(int page);
    }
}
