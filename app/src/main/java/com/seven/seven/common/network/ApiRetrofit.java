package com.seven.seven.common.network;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public class ApiRetrofit {
    private static ApiRetrofit apiRetrofit;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory factory = RxJava2CallAdapterFactory.create();
    private static String baseUrl ="http://www.wanandroid.com/";
    private static ApiServise apiServise;
    public ApiServise  getApiServis(){
        return apiServise;
    }
    public static ApiRetrofit getApi() {
        if (apiRetrofit == null) {
            OkHttpClient.Builder builder =new OkHttpClient.Builder();
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .baseUrl(baseUrl)
                    .build();
            apiServise = retrofit.create(ApiServise.class);
            apiRetrofit=new ApiRetrofit();
        }
        return apiRetrofit;
    }
}
