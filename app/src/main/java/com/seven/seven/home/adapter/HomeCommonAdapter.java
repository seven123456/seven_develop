package com.seven.seven.home.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.seven.R;
import com.seven.seven.common.utils.GlideUtils;
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
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeNewsInfos.NewsInfos item) {
        if (item != null) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_author, "作者: " + item.getAuthor())
                    .setText(R.id.tv_time, "发布时间: " + TimeUtils.longToString(item.getPublishTime(), "yyyy-MM-dd"))
                    .setText(R.id.tv_classify, item.getChapterName());
            if (item.getEnvelopePic() != null) {
               /* Glide.with(mContext).load(item.getEnvelopePic() == null || item.getEnvelopePic().equals("") ? R.drawable.error_logo : item.getEnvelopePic())
                        .apply(requestOptions.placeholder(R.drawable.error_logo).error(R.drawable.error_logo)).into((ImageView) helper.getView(R.id.iv_right));*/
                GlideUtils.loadImageViewLoading((ImageView) helper.getView(R.id.iv_right)
                        ,item.getEnvelopePic() == null || item.getEnvelopePic().equals("") ? R.drawable.error_logo : item.getEnvelopePic(),R.drawable.error_logo,R.drawable.error_logo);
            }
            helper.addOnClickListener(R.id.cd_item);
        }
    }
}
