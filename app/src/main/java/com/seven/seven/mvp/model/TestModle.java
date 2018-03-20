





package com.seven.seven.mvp.model;
import android.support.annotation.NonNull;
import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.base.ResponseCustom;
import com.seven.seven.common.network.ApiRetrofit;
import com.seven.seven.common.network.ThreadSchedulersHelper;
import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.common.base.BaseModel;

import io.reactivex.Observable;

/**
 * Created  on 2018-03-15.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestModle extends BaseModel implements TestContract.ITestModle {
    @NonNull
    public static TestModle newInstance() {
        return new TestModle();
    }

   /* *//*
    * 网络请求
    * *//*
    @Override
    public Observable<TestInfo> getTestInfo(String a1, String a2, String a3) {
        return null;
    }*/
    /*
   * 网络请求
   * */
    @Override
    public Observable<ResponseCustom<Infos>> getTestInfo() {
        return ApiRetrofit.getApiRetrofit().getApiServis().getInfos().compose(ThreadSchedulersHelper.<ResponseCustom<Infos>>rxSchedulers());
    }
}
