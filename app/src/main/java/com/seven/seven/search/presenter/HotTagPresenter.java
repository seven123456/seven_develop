package com.seven.seven.search.presenter;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.base.codereview.BaseRetryWhen;
import com.seven.seven.common.network.ApiException;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.search.contract.SearchContract;
import com.seven.seven.search.events.HotTagEvent;
import com.seven.seven.search.model.HotTagInfos;
import com.seven.seven.search.model.UsingURLinfos;
import com.seven.seven.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by seven
 * on 2018/6/1
 * email:seven2016s@163.com
 */

public class HotTagPresenter extends BasePresenterImpl<SearchContract.View, MainActivity> implements SearchContract.Presenter {
    public HotTagPresenter(SearchContract.View view, MainActivity activity) {
        super(view, activity);
    }

    @Override
    public void getHotTag() {
        HttpObservable.getObservable(apiRetrofit.getHotTags())
                .repeatWhen(new BaseRetryWhen(3, 3000))
                .subscribe(new HttpResultObserver<ResponseCustom<List<HotTagInfos>>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<List<HotTagInfos>> o) {
                        EventBus.getDefault().post(new HotTagEvent(Constans.HOTTAG, o.getData()));
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new HotTagEvent(Constans.HOTTAGERROR, e.getMsg()));
                    }

                });
    }

    @Override
    public void getUsingURL() {
        HttpObservable.getObservable(apiRetrofit.getUsingURL())
                .repeatWhen(new BaseRetryWhen(3, 3000))
                .subscribe(new HttpResultObserver<ResponseCustom<List<UsingURLinfos>>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<List<UsingURLinfos>> o) {
                        EventBus.getDefault().post(new HotTagEvent(Constans.USINGURL, o.getData()));
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new HotTagEvent(Constans.USINGURLERROR, e.getMsg()));
                    }

                });
    }
}
