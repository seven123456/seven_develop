package com.seven.seven.gank.presenter;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.network.ApiException;
import com.seven.seven.common.network.gank.GankIoCustom;
import com.seven.seven.common.network.gank.GankIoObservable;
import com.seven.seven.common.network.gank.GankResultObserver;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.gank.contract.GankContract;
import com.seven.seven.gank.events.GankEvents;
import com.seven.seven.gank.model.GankIoWelfareBean;
import com.seven.seven.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by seven
 * on 2018/6/5
 * email:seven2016s@163.com
 */

public class GankPresenter extends BasePresenterImpl<GankContract.View, MainActivity> implements GankContract.Presenter {
    public GankPresenter(GankContract.View view, MainActivity activity) {
        super(view, activity);
    }

    /*
    * 默认每次10
    * */
    @Override
    public void getGankList() {
        GankIoObservable.getObservable(apiRetrofit.getGankList("http://gank.io/api/data/福利/100/2"))
                .subscribe(new GankResultObserver<GankIoCustom<List<GankIoWelfareBean>>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(GankIoCustom<List<GankIoWelfareBean>> o) {
                        EventBus.getDefault().post(new GankEvents(Constans.GANKIO, o.getResults()));
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new GankEvents(Constans.GANKIOERROR, e.getMsg()));
                    }

                });
    }
}
