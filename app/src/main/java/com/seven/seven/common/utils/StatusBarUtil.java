package com.seven.seven.common.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import java.lang.reflect.Field;

/**
 * Created  on 2017-12-04.
 * author:seven
 * email:seven2016s@163.com
 * 需要什么颜色的状态栏可以自己定义
 * VERSION.SDK_INT >= VERSION_CODES.KITKAT 这句话兼容了4.4
 */
public class StatusBarUtil {
    /**
     * 设置沉浸状态栏，透明底
     *
     * @param activity
     */
    public static void setTranslate(Activity activity, boolean isWhiteIcon) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                if (VERSION.SDK_INT >= 21) {
                    window.clearFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    if (isWhiteIcon) {
                        window.getDecorView()
                                .setSystemUiVisibility(
                                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    } else {
                        window.getDecorView()
                                .setSystemUiVisibility(
                                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                    window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    //设置状态栏颜色
                    window.setStatusBarColor(Color.TRANSPARENT);
                    //API=19 （4.4） 设置透明
                } else if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
                    window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
        }
    }

    /**
     * 设置沉浸状态栏，透明底
     *
     * @param activity
     */
    public static void setTranslateByColor(Activity activity, int color) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                if (VERSION.SDK_INT >= 21) {
                    window.clearFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView()
                            .setSystemUiVisibility(
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(color);
                } else if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
                    window.addFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
        }
    }

    /**
     * 设置沉浸状态栏，黑色底
     *
     * @param activity
     */
    public static void clearTranslate(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                if (VERSION.SDK_INT >= 21) {
                    window.clearFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView()
                            .setSystemUiVisibility(
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    window.addFlags(LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.BLACK);
                } else if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
                    window.clearFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
        }
    }

    private static int sStatusBarHeight;

    /**
     * 获取状态栏高度
     *
     * @param resources
     * @return
     */
    private static int getStatusBarHeight(Resources resources) {
        if (sStatusBarHeight <= 0 && resources != null) {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                sStatusBarHeight = resources.getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sStatusBarHeight;
    }

    /**
     * 获取需要适配的状态栏高度，如果是KITKAT一下返回0
     *
     * @param resources
     * @return
     */
    public static int getStatusBarHeightFit(Resources resources) {
        return VERSION.SDK_INT >= VERSION_CODES.KITKAT ? getStatusBarHeight(resources) : 0;
    }
}
