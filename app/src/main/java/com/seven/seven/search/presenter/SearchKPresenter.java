package com.seven.seven.search.presenter;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.network.ApiException;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.search.SearchDetailActivity;
import com.seven.seven.search.contract.SearchKContract;
import com.seven.seven.search.events.SearchKEvent;
import com.seven.seven.search.model.SearchListInfos;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;

/**
 * Created by seven
 * on 2018/6/2
 * email:seven2016s@163.com
 */

public class SearchKPresenter extends BasePresenterImpl<SearchKContract.View, SearchDetailActivity> implements SearchKContract.Presenter {

    public SearchKPresenter(SearchKContract.View view, SearchDetailActivity activity) {
        super(view, activity);
    }

    @Override
    public void searchK(int page, Object k) {
        HttpObservable.getObservable(apiRetrofit.searchK(page, k))
                .subscribe(new HttpResultObserver<ResponseCustom<SearchListInfos>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<SearchListInfos> o) {
                        if (o.getData() != null) {
                            EventBus.getDefault().post(new SearchKEvent(Constans.SEARCHK, o.getData()));
                        }
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new SearchKEvent(Constans.SEARCHKERROR, e.getMsg()));
                    }

                });
    }
}
