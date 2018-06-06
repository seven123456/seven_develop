package com.seven.seven.search;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seven.seven.R;
import com.seven.seven.common.utils.HTMLScript;
import com.seven.seven.common.utils.TimeUtils;
import com.seven.seven.search.model.SearchListInfos;

import java.util.List;

/**
 * Created by seven
 * on 2018/6/2
 * email:seven2016s@163.com
 */

public class SearchAdapter extends BaseQuickAdapter<SearchListInfos.DatasBean, BaseViewHolder> {
    public SearchAdapter(int layoutResId, @Nullable List<SearchListInfos.DatasBean> data) {
        super(layoutResId, data);
    }

    public SearchAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchListInfos.DatasBean item) {
        if (item != null) {
            /*String title =item.getTitle();
            title =title.replace("<em class='highlight>","");
            title =title.replace("</em>","");*/
            helper.setText(R.id.tv_title, "标题：" + HTMLScript.delHTMLTag(item.getTitle()))
                    .setText(R.id.tv_type, "类型：" + item.getChapterName())
                    .setText(R.id.tv_time, "发布时间：" + TimeUtils.longToString(item.getPublishTime(), "yyyy-MM-dd HH-mm-ss"))
                    .setText(R.id.tv_author, "作者：" + item.getAuthor());
            helper.addOnClickListener(R.id.card_view);
        }
    }
}
