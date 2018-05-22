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

import java.util.List;

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

    @Override
    public void getHomeData() {
        HttpObservable.getObservable(ApiRetrofit.getApiRetrofit().getApiServis().getHomeNewsInfos())
                .subscribe(new HttpResultObserver<ResponseCustom<HomeNewsInfos>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<HomeNewsInfos> responseCustom) {
//                        if (o.isSuccess()) {
                        if (getView() != null) {
                            EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATA, responseCustom.getData()));
                        }
//                        }
                    }

                    @Override
                    protected void onFail(Throwable error) {
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATAFIAL, error.getMessage()));
                    }
                });
    }

    @Override
    public void getHomeBanner() {
        HttpObservable.getObservable(ApiRetrofit.getApiRetrofit().getApiServis().getBannerInfos())
                .subscribe(new HttpResultObserver<ResponseCustom<List<HomeBannerInfos>>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<List<HomeBannerInfos>> responseCustom) {
                        if (getView() != null) {
                            EventBus.getDefault().post(new HomeEvents<>(Constans.HOMEBANNER, responseCustom.getData()));
                        }
                    }

                    @Override
                    protected void onFail(Throwable error) {
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATAFIAL, error.getMessage()));
                    }
                });
    }
}
