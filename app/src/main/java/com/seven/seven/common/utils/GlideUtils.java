package com.seven.seven.common.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.seven.seven.ui.MyApplication;

/**
 * Created  on 2018-05-28.
 * author:seven
 * email:seven2016s@163.com
 */

public class GlideUtils {
    /*
    * 正常加载
    * */
    public static void loadImageview(ImageView imageview, String path) {
        Glide.with(MyApplication.getContext()).load(path).into(imageview);
    }

    /*
    *
    * 加载带有占位图的view
    * */
    public static void loadImageViewLoading(ImageView imageView, Object path, int errorPath, int loadingPath) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(MyApplication.getContext()).load(path)
                .apply(requestOptions.placeholder(loadingPath).error(errorPath))
                .into(imageView);
    }

    /*
    * 加载指定大小的image
    * */
    public static void loadImageViewSize(ImageView imageView, String path, int loadingPath, int errorPath, int width, int height) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(MyApplication.getContext()).load(path).apply(requestOptions.override(width, height)
                .placeholder(loadingPath).error(errorPath))
                .into(imageView);
    }

    /*
    * 裁剪大小，设置缓存模式，防止oom(不缓存)
    *
    * */
    public static void loadImageViewOOM(ImageView imageView, int loadingPath, int errorPath, String path, int height, int width) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(MyApplication.getContext()).load(path)
                .apply(requestOptions.override(width, height).skipMemoryCache(true)
                        .placeholder(loadingPath).error(errorPath))
                .into(imageView);
    }

    /*
    * 加载圆形图片
    * */
    public static void loadImageViewCircle(ImageView imageView, String path, int loadingPath, int errorPath) {
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(MyApplication.getContext()).asBitmap().load(path)
                .apply(requestOptions.skipMemoryCache(true).optionalCircleCrop()
                        .placeholder(loadingPath).error(errorPath))
                .into(imageView);
    }
}
