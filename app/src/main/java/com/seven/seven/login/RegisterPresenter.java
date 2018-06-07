package com.seven.seven.login;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.base.codereview.BaseRetryWhen;
import com.seven.seven.common.network.ApiException;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created  on 2018-05-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View, RejisterActivity> implements RegisterContract.Presenter {
    public RegisterPresenter(RegisterContract.View view, RejisterActivity activity) {
        super(view, activity);
    }


    @Override
    public void register(String username, String password, String repassword) {
   /*     HttpObservable.getObservable(apiRetrofit.register(username, password, repassword))
                .subscribe(new HttpResultObserver<ResponseCustom<RegisterInfo>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<RegisterInfo> o) {
                        if (getView() != null) {
//                            if (o.isSuccess()) {
                                EventBus.getDefault().post(new LoginEvent(Constans.REGISTER, o.getData()));
//                            } else {
//                                EventBus.getDefault().post(new LoginEvent(Constans.USERERROR, o.getErrorMsg()));
//                            }
                        }
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new LoginEvent(Constans.USERERROR, e.getMsg()));
                    }
                });*/
        HttpObservable.getObservable(apiRetrofit.register(username, password, repassword))
                .retryWhen(new BaseRetryWhen(3, 1000))
                .flatMap(new Function<ResponseCustom<RegisterInfo>, Observable<ResponseCustom<RegisterInfo>>>() {
                    @Override
                    public Observable<ResponseCustom<RegisterInfo>> apply(ResponseCustom<RegisterInfo> registerInfo) throws Exception {
                        return HttpObservable.getObservable(apiRetrofit.login(registerInfo.getData().getUsername(), registerInfo.getData().getPassword()));
                    }
                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultObserver<ResponseCustom<RegisterInfo>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<RegisterInfo> o) {
                        if (o.isSuccess()) {
                            EventBus.getDefault().post(new RegisterEvent(Constans.REGISTER, o.getData()));
                        } else {
                            EventBus.getDefault().post(new RegisterEvent(Constans.USERERRORS, o.getErrorMsg()));
                        }
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        if (e.getCode() == 1000) {
                            EventBus.getDefault().post(new RegisterEvent(Constans.USERERRORS, "账号已被注册"));
                        } else {
                            EventBus.getDefault().post(new LoginEvent(Constans.USERERRORS, e.getMsg()));
                        }
                    }
                });
    }
}
