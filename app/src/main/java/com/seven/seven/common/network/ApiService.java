package com.seven.seven.common.network;

import com.seven.seven.common.Model.BannerInfos;
import com.seven.seven.home.events.HomeBannerInfos;
import com.seven.seven.home.model.HomeNewsInfos;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public interface ApiService {

    @GET("/article/list/0/json")
    Observable<ResponseCustom<HomeNewsInfos>> getHomeNewsInfos();

    @GET("banner/json")
    Observable<ResponseCustom<List<HomeBannerInfos>>> getBannerInfos();
}
