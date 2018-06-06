package com.seven.seven.gank.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.seven.R;
import com.seven.seven.common.utils.GlideUtils;
import com.seven.seven.gank.model.GankIoWelfareBean;

import java.util.List;

/**
 * Created by seven
 * on 2018/6/5
 * email:seven2016s@163.com
 */

public class GankAdapter extends BaseQuickAdapter<GankIoWelfareBean, BaseViewHolder> {
    public GankAdapter(int layoutResId, @Nullable List<GankIoWelfareBean> data) {
        super(layoutResId, data);
    }

    public GankAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoWelfareBean item) {
        GlideUtils.loadImageViewLoading((ImageView) helper.getView(R.id.iv_item_image), item.getUrl(), R.drawable.ic_error_logo, R.drawable.ic_error_logo);
        helper.addOnClickListener(R.id.iv_item_image);
    }
}
