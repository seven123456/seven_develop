package com.seven.seven.ui.base;

import android.support.annotation.NonNull;

/**
 * Created  on 2018-01-05.
 * author:seven
 * email:seven2016s@163.com
 */

public interface BaseView {
    /**
     * 初始化presenter
     * <p>
     * 此方法返回的presenter对象不可为空
     */
    @NonNull
    BasePresenter initPresenter();

    /**
     * 显示toast消息
     *
     * @param msg 要显示的toast消息字符串
     */
    void showToast(String msg);

    /**
     * 显示等待dialog
     *
     * @param waitMsg 等待消息字符串
     */
    void showWaitDialog(String waitMsg);

    /**
     * 隐藏等待dialog
     */
    void hideWaitDialog();

    /**
     * 隐藏键盘
     */
    void hideKeybord();

    /**
     * 回退
     */
    void back();
}
