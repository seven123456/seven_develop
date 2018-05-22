package com.seven.seven.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.AppBarStateChangeListener;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.home.model.HomeNewsInfos;
import com.seven.seven.home.model.HomeToWebViewInfo;

/**
 * Created  on 2018-05-22.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeNewsDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private HomeToWebViewInfo homeToWebViewInfo;
    private HomeNewsDetailActivity mActivity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mActivity = HomeNewsDetailActivity.this;
        homeToWebViewInfo = (HomeToWebViewInfo) getIntent().getSerializableExtra("newsInfo");
        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBarLayout = findViewById(R.id.abl_appbar_layout);
        collapsingToolbarLayout = findViewById(R.id.ctl_collapsing);
        imageView = findViewById(R.id.image_view);


    }

    @Override
    protected void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setLisenter() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppManager.getAppManager().finishActivity(HomeNewsDetailActivity.this);
                onBackPressed();
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case EXPANDED://展开
                        StatusBarUtil.setTranslate(mActivity, true);
                        Glide.with(HomeNewsDetailActivity.this).load(homeToWebViewInfo.imgUrl == null || homeToWebViewInfo.imgUrl.equals("") ? R.drawable.timg
                                : homeToWebViewInfo.imgUrl).into(imageView);
                        collapsingToolbarLayout.setTitle(homeToWebViewInfo.title);
                        break;
                    case COLLAPSED://折叠
                        /*折叠后toolbar的颜色*/
                        StatusBarUtil.setTranslateByColor(mActivity, getResources().getColor(R.color.red));
                        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.red));
                        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
                        collapsingToolbarLayout.setTitle("热门博客");
                        break;
                }
            }
        });
    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_home_news_detail;
    }
}
