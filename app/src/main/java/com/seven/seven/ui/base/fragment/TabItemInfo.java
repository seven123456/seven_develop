package com.seven.seven.ui.base.fragment;

import android.support.v4.app.Fragment;

import java.io.Serializable;

/**
 * Created by seven
 * on 2018/4/6
 * email:seven2016s@163.com
 */

public class TabItemInfo implements Serializable {
    public Fragment fragment;
    public int iconResId;
    public int nameResId;

    public TabItemInfo(Fragment fragment, int iconResId, int nameResId) {
        this.fragment = fragment;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
    }
}
