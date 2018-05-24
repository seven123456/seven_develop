package com.seven.seven.common.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private static volatile ApiRetrofit apiRetrofit;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory adapterFactory = RxJava2CallAdapterFactory.create();
    private static final int CONNECT_TIME_OUT = 10;
    private static final int READ_TIME_OUT = 10;
    private static String baseUrl = NetworkUrl.ANDROID_TEST_SERVICE;
    private static ApiService apiServise;

    public  ApiService getApiServis() {
        return apiServise;
    }

    public static ApiRetrofit getApiRetrofit() {
        if (apiRetrofit == null) {
            synchronized (ApiRetrofit.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    private ApiRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)/*读写链接超时*/
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIME_OUT, TimeUnit.SECONDS)

                .addInterceptor(httpLoggingInterceptor)//打印log日志
                .retryOnConnectionFailure(true)//失败重连
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(okHttpClient)
                .baseUrl(NetworkUrl.getNetWorkName())
                .build();
        apiServise = retrofit.create(ApiService.class);
    }

    /*
    * 打印日志拦截器
    * */
    private static final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
}
