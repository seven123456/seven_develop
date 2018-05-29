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
import com.seven.seven.user.events.CollectEvent;
import com.seven.seven.user.model.CollectListInfos;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class CollectPresenter extends BasePresenterImpl<CollectContract.View, CollectListActivity> implements CollectContract.Presenter {
    public CollectPresenter(CollectContract.View view, CollectListActivity activity) {
        super(view, activity);
    }

    @Override
    public void getCollectList(int page) {
        HttpObservable.getObservable(apiRetrofit.getCoolectList(page))
                .subscribe(new HttpResultObserver<ResponseCustom<CollectListInfos>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<CollectListInfos> o) {
                        EventBus.getDefault().post(new CollectEvent(Constans.COLLECTLIST, o.getData()));
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new CollectEvent(Constans.COLLECTLISTERROR, e.getMsg()));
                    }

                });
    }
}
