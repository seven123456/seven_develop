package com.seven.seven.home.presenter;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.network.ApiException;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.home.HomeNewsDetailActivity;
import com.seven.seven.home.contract.BaseWebviewContract;
import com.seven.seven.home.events.BaseWebViewEvents;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class BaseWebviewPresenter extends BasePresenterImpl<BaseWebviewContract.View, HomeNewsDetailActivity> implements BaseWebviewContract.Presenter {
    public BaseWebviewPresenter(BaseWebviewContract.View view, HomeNewsDetailActivity activity) {
        super(view, activity);
    }

    @Override
    public void collectBlog(int id) {
        HttpObservable.getObservable(apiRetrofit.collectBlog(id))
                .subscribe(new HttpResultObserver<ResponseCustom<Object>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<Object> o) {
                        EventBus.getDefault().post(new BaseWebViewEvents(Constans.COLLECT, o.getData()));
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new BaseWebViewEvents(Constans.COLLECTERROR, e.getMsg()));
                    }
                });
    }
}
