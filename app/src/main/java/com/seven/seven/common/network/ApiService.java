package com.seven.seven.common.network;

import com.seven.seven.common.Model.BannerInfos;
import com.seven.seven.common.Model.Infos;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public interface ApiService {

    @GET("/article/list/1/json")
    Observable<ResponseCustom<Infos>> getInfos();

    @GET("banner/json")
    Observable<ResponseCustom<BannerInfos>> getBannerInfos();
}
