package com.seven.seven.home.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.seven.R;
import com.seven.seven.home.model.HomeBannerInfos;

import java.util.List;

/**
 * Created  on 2018-05-22.
 * author:seven
 * email:seven2016s@163.com
 */

public class BannerViewAdapter extends BaseQuickAdapter<HomeBannerInfos, BaseViewHolder> {
    private Context mContext;

    public BannerViewAdapter(int layoutResId, @Nullable List<HomeBannerInfos> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    public BannerViewAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBannerInfos item) {
        if (item != null) {
            Glide.with(mContext).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_banner_image));
            helper.addOnClickListener(R.id.iv_banner_image);
        }
    }
}
