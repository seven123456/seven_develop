package com.seven.seven.user.presenter;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.network.ApiException;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.ui.MainActivity;
import com.seven.seven.user.CollectListActivity;
import com.seven.seven.user.contract.CollectContract;
import com.seven.seven.user.contract.UserInfoContract;
import com.seven.seven.user.model.CollectListInfos;
import com.seven.seven.user.userevent.CollectEvent;
import com.seven.seven.user.userevent.UserInfoEvent;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class UserInfoPresenter extends BasePresenterImpl<UserInfoContract.View, MainActivity> implements UserInfoContract.Presenter {

    public UserInfoPresenter(UserInfoContract.View view, MainActivity activity) {
        super(view, activity);
    }

    @Override
    public void getCollectSize(int page) {
        HttpObservable.getObservable(apiRetrofit.getCoolectList(page))
                .subscribe(new HttpResultObserver<ResponseCustom<CollectListInfos>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<CollectListInfos> o) {
                        EventBus.getDefault().post(new UserInfoEvent(Constans.COLLECTSIZE, o.getData()));
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new UserInfoEvent(Constans.COLLECTSIZEERROR, e.getMsg()));
                    }

                });
    }
}
