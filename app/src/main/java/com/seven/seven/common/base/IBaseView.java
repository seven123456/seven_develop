package com.seven.seven.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.seven.seven.common.base.codereview.BaseView;

/**
 * Created  on 2018-03-15.
 * author:seven
 * email:seven2016s@163.com
 */

public interface IBaseView extends BaseView {
    /**
     * 跳往新的Activity
     *
     * @param clz 要跳往的Activity
     */
    void startNewActivity(@NonNull Class<?> clz);

    /**
     * 跳往新的Activity
     *
     * @param clz    要跳往的Activity
     * @param bundle 携带的bundle数据
     */
    void startNewActivity(@NonNull Class<?> clz, Bundle bundle);

    /**
     * 跳往新的Activity
     * @param clz 要跳转的Activity
     * @param bundle bundel数据
     * @param requestCode requestCode
     */
    void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode);
}
