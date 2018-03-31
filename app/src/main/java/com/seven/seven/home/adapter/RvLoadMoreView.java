package com.seven.seven.home.adapter;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.seven.seven.R;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */
public class RvLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.layout_recycler_loadmore;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}