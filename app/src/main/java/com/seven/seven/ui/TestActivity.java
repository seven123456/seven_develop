package com.seven.seven.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.base.BaseActivity;

/**
 * Created  on 2018-02-07.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestActivity extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RecyclerView recyclerView = findViewById(R.id.rv_like);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter();
        TextView textVie = findViewById(R.id.xixi);
        String text = this.getResources().getString(R.string.xixi);
      /*   tv_like.text = Html.fromHtml(text)*/
        textVie.setText(Html.fromHtml(text));

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_home_me;
    }
}
