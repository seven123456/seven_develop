package com.seven.seven.common.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.PreferencesUtils;
import com.seven.seven.ui.MyApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.seven.seven.common.utils.Constans.COOKIE_PREF;

/**
 * Created  on 2018-05-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class AddCookieInterceptor implements Interceptor {
    public static String PREFERENCE_NAME = "Config";

    /*  private Context mContext;

      public AddCookieInterceptor(Context context) {
          this.mContext = context;
      }
  */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String cookie = getCookie(request.url().toString(), request.url().host());
        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("cookie", cookie);
        }
        return chain.proceed(builder.build());
    }

    private String getCookie(String url, String host) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        /*if (!TextUtils.isEmpty(url) && sp.contains(url) && !TextUtils.isEmpty(sp.getString(url, ""))) {
            return sp.getString(url, "");
        }
        if (!TextUtils.isEmpty(host) && sp.contains(host) && !TextUtils.isEmpty(sp.getString(host, ""))) {
            return sp.getString(host, "");
        }*/
        return sp.getString(Constans.COOKIE_PREF, null);
    }
}
