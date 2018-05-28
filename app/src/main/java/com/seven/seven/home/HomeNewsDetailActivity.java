package com.seven.seven.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.AppBarStateChangeListener;
import com.seven.seven.common.utils.GlideUtils;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.common.view.webview.H5Control;
import com.seven.seven.common.view.webview.SevenWebView;
import com.seven.seven.home.model.HomeToWebViewInfo;

/**
 * Created  on 2018-05-22.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeNewsDetailActivity extends BaseActivity implements H5Control {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private HomeToWebViewInfo homeToWebViewInfo;
    private HomeNewsDetailActivity mActivity;
    private SevenWebView webView;

    @SuppressLint("WrongViewCast")
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
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.topMargin = StatusBarUtil.getStatusBarHeight(this);
        webView = findViewById(R.id.wv_webview);
        webView.getH5JsInterface().registerListener(this);
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(homeToWebViewInfo.h5Url);
            }
        });
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
//                        StatusBarUtil.setTranslate(mActivity, true);
                        /*Glide.with(HomeNewsDetailActivity.this).load(homeToWebViewInfo.imgUrl == null || homeToWebViewInfo.imgUrl.equals("") ? R.drawable.seven_logo
                                : homeToWebViewInfo.imgUrl).into(imageView);*/
//                        collapsingToolbarLayout.setTitle(homeToWebViewInfo.title);
                        GlideUtils.loadImageViewLoading(imageView, homeToWebViewInfo.imgUrl == null || homeToWebViewInfo.imgUrl.equals("") ? R.drawable.seven_logo
                                : homeToWebViewInfo.imgUrl, R.drawable.error_logo, R.drawable.error_logo);
                        collapsingToolbarLayout.setTitle(" ");
                        break;
                    case COLLAPSED://折叠
                        /*折叠后toolbar的颜色*/
//                        StatusBarUtil.setTranslateByColor(mActivity, getResources().getColor(R.color.red));
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
    protected boolean isNeedTranslateBar() {
        return true;
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_home_news_detail;
    }

    @Override
    public void H5ControlAndroidEvent(String url, Bundle bundle) {

    }

    @Override
    protected void onDestroy() {
        webView.getH5JsInterface().unRegisterListener();
        super.onDestroy();
    }
}
