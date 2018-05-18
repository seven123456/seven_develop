package com.seven.seven.home.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.seven.R;
import com.seven.seven.common.utils.TimeUtils;
import com.seven.seven.home.model.HomeNewsInfos;

import java.util.List;

/**
 * Created  on 2018-05-18.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeCommonAdapter extends BaseQuickAdapter<HomeNewsInfos.NewsInfos, BaseViewHolder> {
    private Context mContext;

    public HomeCommonAdapter(int layoutResId, @Nullable List<HomeNewsInfos.NewsInfos> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeNewsInfos.NewsInfos item) {
        if (item != null) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_author, item.getAuthor())
                    .setText(R.id.tv_time, TimeUtils.longToString(item.getPublishTime(), "yyyy-MM-dd HH:mm:ss"))
                    .setText(R.id.tv_classify, item.getChapterName());
            if (item.getEnvelopePic() != null) {
                Glide.with(mContext).load(item.getEnvelopePic())
                        .into((ImageView) helper.getView(R.id.iv_right));
            }
        }
    }
}
