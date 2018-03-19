package com.seven.seven.common.network;

import com.seven.seven.common.Model.BannerInfos;
import com.seven.seven.common.Model.Infos;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public interface ApiServise {

    @GET("/article/list")
    Observable<List<Infos>> getInfos();

    @GET("banner/json")
    Observable<List<BannerInfos>>  getBannerInfos();
}
