package com.seven.seven.home.presenter;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.base.codereview.BaseRetryWhen;
import com.seven.seven.common.network.ApiRetrofit;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.home.contract.HomeContract;
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

public class HomePresenter extends BasePresenterImpl<HomeContract.View, MainActivity> implements HomeContract.Presenter {
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

    // 设置变量
    // 可重试次数
    private int maxConnectCount = 10;
    // 当前已重试次数
    private int currentRetryCount = 0;
    // 重试等待时间
    private int waitRetryTime = 0;

    @Override
    public void getHomeBanner() {
        io.reactivex.Observable observable = HttpObservable.getObservable(apiRetrofit.getHomeNewsInfos());
        io.reactivex.Observable observable1 = HttpObservable.getObservable(apiRetrofit.getBannerInfos());
        io.reactivex.Observable.mergeDelayError(observable, observable1).retryWhen(new BaseRetryWhen(3, 3000)).subscribe(new HttpResultObserver<ResponseCustom<Object>>() {
            @Override
            protected void onLoading(Disposable d) {

            }

            @Override
            protected void onSuccess(ResponseCustom<Object> responseCustom) {
                if (getView() != null) {
                    if (responseCustom.getData() != null) {
                        count++;
                    }
                    if (responseCustom.getData() instanceof HomeNewsInfos) {
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATA, responseCustom.getData()));
                    } else if (responseCustom.getData() instanceof List) {
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEBANNER, responseCustom.getData()));
                    }
                    if (count == 2) {
                        count = 0;
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEDASUCCESS, "成功"));
                    } /*else {
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEERROR, "只有一次"));
                    }*/
                }
            }

            @Override
            protected void onFail(Throwable error) {
                EventBus.getDefault().post(new HomeEvents(Constans.HOMEERROR, error.getMessage()));
            }
        });
    }

    @Override
    public void getMoreHomeData(int pageNum) {
        HttpObservable.getObservable(ApiRetrofit.getApiRetrofit().getApiServis().getMoreHomeNewsInfos(pageNum))
                .subscribe(new HttpResultObserver<ResponseCustom<HomeNewsInfos>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<HomeNewsInfos> responseCustom) {
                        if (getView() != null) {
                            if (responseCustom.getData() != null) {
                                EventBus.getDefault().post(new HomeEvents(Constans.HOMEDATA, responseCustom.getData()));
                            }
                        }
                    }

                    @Override
                    protected void onFail(Throwable error) {
                        EventBus.getDefault().post(new HomeEvents(Constans.HOMEERROR, error.getMessage()));
                    }
                });
    }
}
