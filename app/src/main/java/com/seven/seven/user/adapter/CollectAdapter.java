package com.seven.seven.user.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.seven.R;
import com.seven.seven.common.utils.TimeUtils;
import com.seven.seven.user.model.CollectInfo;
import com.seven.seven.user.model.CollectListInfos;

import java.util.List;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class CollectAdapter extends BaseItemDraggableAdapter<CollectInfo, BaseViewHolder> {

    public CollectAdapter(int layoutResId, @Nullable List<CollectInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectInfo item) {
        if (item != null) {
            helper.setText(R.id.tv_time, "发布时间:" + TimeUtils.longToString(item.getPublishTime(), "yyyy-MM-dd dd-hh-ss"))
                    .setText(R.id.tv_author, "作者:" + item.getAuthor())
                    .setText(R.id.tv_title, item.getTitle())
                    .addOnClickListener(R.id.cv_collect);


        }
    }
}
