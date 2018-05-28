package com.seven.seven.login;

import android.util.Log;

import com.seven.seven.common.base.codereview.BasePresenterImpl;
import com.seven.seven.common.base.codereview.BaseRetryWhen;
import com.seven.seven.common.network.ApiException;
import com.seven.seven.common.network.HttpObservable;
import com.seven.seven.common.network.HttpResultObserver;
import com.seven.seven.common.network.ResponseCustom;
import com.seven.seven.common.utils.Constans;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created  on 2018-05-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View, LoginActivity> implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.View view, LoginActivity activity) {
        super(view, activity);
    }

    @Override
    public void login(String username, String password) {

        HttpObservable.getObservable(apiRetrofit.login(username, password))
                .subscribe(new HttpResultObserver<ResponseCustom<RegisterInfo>>() {
                    @Override
                    protected void onLoading(Disposable d) {

                    }

                    @Override
                    protected void onSuccess(ResponseCustom<RegisterInfo> o) {
                        if (getView() != null) {
                            if (o.isSuccess()) {
                            EventBus.getDefault().post(new LoginEvent(Constans.LOGIN, o.getData()));
                            } else {
                                EventBus.getDefault().post(new LoginEvent(Constans.USERERROR, o.getErrorMsg()));
                            }
                        }
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new LoginEvent(Constans.USERERROR, e.getMsg()));
                    }
                });
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
                            EventBus.getDefault().post(new LoginEvent(Constans.LOGIN, o.getData()));
                        }else {
                            EventBus.getDefault().post(new LoginEvent(Constans.USERERROR, o.getErrorMsg()));
                        }
                    }

                    @Override
                    protected void onFail(ApiException e) {
                        EventBus.getDefault().post(new LoginEvent(Constans.USERERROR, e.getMsg()));
                    }
                });
    }
}
