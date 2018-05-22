package com.seven.seven.home.presenter;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.network.ApiRetrofit;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.home.contract.HomeContract;
import com.seven.seven.home.events.HomeBannerInfos;
import com.seven.seven.home.events.HomeEvents;
import com.seven.seven.home.model.HomeNewsInfos;
import com.seven.seven.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View, MainActivity> implements HomeContract.Parenter {
    public HomePresenter(HomeContract.View view, MainActivity activity) {
        super(view, activity);
    }

    private int count = 0;

    @Override
    public void getHomeData() {
        HttpObservable.getObservable(ApiRetrofit.getApiRetrofit().getApiServis().getHomeNewsInfos())
                .subscribe(new HttpResultObserver<ResponseCustom<HomeNewsInfos>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<HomeNewsInfos> responseCustom) {
                        if (getView() != null) {
                            EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATA, responseCustom.getData()));
                        }
                    }

                    @Override
                    protected void onFail(Throwable e) {

                    }
                });
    }

    @Override
    public void getHomeBanner() {
        io.reactivex.Observable observable = HttpObservable.getObservable(ApiRetrofit.getApiRetrofit().getApiServis().getHomeNewsInfos());
        io.reactivex.Observable observable1 = HttpObservable.getObservable(ApiRetrofit.getApiRetrofit().getApiServis().getBannerInfos());
        io.reactivex.Observable.concat(observable, observable1)
//        HttpObservable.getObservable(ApiRetrofit.getApiRetrofit().getApiServis().getHomeNewsInfos())
                .subscribe(new HttpResultObserver<ResponseCustom<Object>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<Object> responseCustom) {
                        if (getView() != null) {
                            count++;
                            if (responseCustom.getData() instanceof HomeNewsInfos) {
                                EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATA, responseCustom.getData()));
                            } else if (responseCustom.getData() instanceof List) {
                                EventBus.getDefault().post(new HomeEvents<>(Constans.HOMEBANNER, responseCustom.getData()));
                            }
                            if (count == 2) {
                                count = 0;
                                EventBus.getDefault().post(new HomeEvents<>(Constans.HOMEDASUCCESS, "103"));
                            }
                        }
                    }

                    @Override
                    protected void onFail(Throwable error) {
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATAFIAL, error.getMessage()));
                    }
                });
    }
}
