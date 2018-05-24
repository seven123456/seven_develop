package com.seven.seven.common.network;

import com.seven.seven.common.Model.BannerInfos;
import com.seven.seven.home.events.HomeBannerInfos;
import com.seven.seven.home.model.HomeNewsInfos;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public interface ApiService {
    /*获取首页文章*/
    @GET("/article/list/0/json")
    Observable<ResponseCustom<HomeNewsInfos>> getHomeNewsInfos();

    /*获取首页banner*/
    @GET("banner/json")
    Observable<ResponseCustom<List<HomeBannerInfos>>> getBannerInfos();

    /*获取首页文章,分页  @Path 替换url中的参数*/
    @GET("/article/list/{page}/json")
    Observable<ResponseCustom<HomeNewsInfos>> getMoreHomeNewsInfos(
            @Path("page") int page
    );
}
