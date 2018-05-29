package com.seven.seven.common.network;

import com.seven.seven.home.model.HomeBannerInfos;
import com.seven.seven.home.model.HomeNewsInfos;
import com.seven.seven.login.RegisterInfo;
import com.seven.seven.user.model.CollectListInfos;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public interface ApiService {
    /*获取首页文章*/
    @GET("/article/list/0/json")
    Flowable<ResponseCustom<HomeNewsInfos>> getHomeNewsInfos();

    /*获取首页banner*/
    @GET("banner/json")
    Flowable<ResponseCustom<List<HomeBannerInfos>>> getBannerInfos();

    /*获取首页文章,分页  @Path 替换url中的参数*/
    @GET("/article/list/{page}/json")
    Observable<ResponseCustom<HomeNewsInfos>> getMoreHomeNewsInfos(
            @Path("page") int page
    );

    @FormUrlEncoded
    @POST("/user/register")
    Observable<ResponseCustom<RegisterInfo>> register(@Field("username") String username,
                                                      @Field("password") String password,
                                                      @Field("repassword") String repassword
    );

    @FormUrlEncoded
    @POST("/user/login")
    Observable<ResponseCustom<RegisterInfo>> login(@Field("username") String username,
                                                   @Field("password") String password);

    /*
    * 根据ID收藏站内文章
    * */
    @POST("/lg/collect/{id}/json")
    Observable<ResponseCustom<Object>> collectBlog(
            @Path("id") int id
    );

    /*获取收藏文章,分页  @Path 替换url中的参数*/
    @GET("/lg/collect/list/{page}/json")
    Observable<ResponseCustom<CollectListInfos>> getCoolectList(
            @Path("page") int page
    );
}
