package com.seven.seven.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract  class BaseLoadMoreAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    public BaseLoadMoreAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        init();
    }

    public BaseLoadMoreAdapter(@Nullable List<T> data) {
        super(data);
        init();
    }

    public BaseLoadMoreAdapter(int layoutResId) {
        super(layoutResId);
        init();
    }

    protected  void init(){
        setLoadMoreView(new RvLoadMoreView());
        setEnableLoadMore(true);
        openLoadAnimation();//开启默认动画载入（仅开启加载新item时开启动画）
    }


}
