package com.seven.seven.home;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.AppBarStateChangeListener;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.GlideUtils;
import com.seven.seven.common.utils.ShareUtils;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.common.view.NestedScrollWebView;
import com.seven.seven.common.view.dialog.ShareMenuPop;
import com.seven.seven.common.view.webview.H5Control;
import com.seven.seven.home.contract.BaseWebviewContract;
import com.seven.seven.home.events.BaseWebViewEvents;
import com.seven.seven.home.model.HomeToWebViewInfo;
import com.seven.seven.home.presenter.BaseWebviewPresenter;
import com.seven.seven.login.LoginActivity;
import com.seven.seven.search.LookImageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created  on 2018-05-22.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeNewsDetailActivity extends BaseActivity implements H5Control, BaseWebviewContract.View {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView, ivFollow, ivMenu;
    private HomeToWebViewInfo homeToWebViewInfo;
    private HomeNewsDetailActivity mActivity;
    private NestedScrollWebView webView;
    private BaseWebviewPresenter baseWebviewPresenter;
    private CoordinatorLayout coordinatorLayout;

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mActivity = HomeNewsDetailActivity.this;
        baseWebviewPresenter = new BaseWebviewPresenter(this, this);
        homeToWebViewInfo = (HomeToWebViewInfo) getIntent().getSerializableExtra("newsInfo");
        toolbar = findViewById(R.id.toolbar);
        coordinatorLayout = findViewById(R.id.cl_constraint);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appBarLayout = findViewById(R.id.abl_appbar_layout);
        collapsingToolbarLayout = findViewById(R.id.ctl_collapsing);
        imageView = findViewById(R.id.image_view);
        ivFollow = findViewById(R.id.iv_follow);
        ivMenu = findViewById(R.id.iv_menu);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.topMargin = StatusBarUtil.getStatusBarHeight(this);
        initWebview();
    }

    /*
    * 动态添加webview，解决oom
    * */
    private void initWebview() {
        webView = findViewById(R.id.lly_webview_root);
//        LinearLayout mLayout = findViewById(R.id.lly_webview_root);
        /*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webView = new SevenWebView(getApplicationContext());
        webView.setLayoutParams(layoutParams);
        mLayout.addView(webView);*/
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
                                : homeToWebViewInfo.imgUrl, R.drawable.ic_error_logo, R.drawable.ic_error_logo);
                        collapsingToolbarLayout.setTitle(" ");
                        ivFollow.setVisibility(View.GONE);
                        ivMenu.setVisibility(View.GONE);
                        break;
                    case COLLAPSED://折叠
                        /*折叠后toolbar的颜色*/
//                        StatusBarUtil.setTranslateByColor(mActivity, getResources().getColor(R.color.red));
                        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.red));
                        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
                        collapsingToolbarLayout.setTitle("热门博客");
                        if (homeToWebViewInfo != null) {
                            if (homeToWebViewInfo.collect == true) {
                                ivFollow.setVisibility(View.GONE);
                            } else {
                                ivFollow.setVisibility(View.VISIBLE);
                            }
                            ivMenu.setVisibility(View.VISIBLE);
                            ObjectAnimator rotationAnimator = ObjectAnimator
                                    .ofFloat(ivFollow, "rotation", -20, 20, -20, 20, -20, 20, 0).setDuration(600);
                            rotationAnimator.setStartDelay(200);
                            rotationAnimator.setDuration(1000);
                            rotationAnimator.start();
                        }
                        break;
                }
            }
        });
        ivFollow.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_follow:
                baseWebviewPresenter.collectBlog(homeToWebViewInfo.id);
                break;
            case R.id.iv_menu:
                ShareMenuPop shareMenuPop = new ShareMenuPop(this);
                shareMenuPop.show(coordinatorLayout);
                shareMenuPop.setPopListenter(new ShareMenuPop.sharePopListenter() {
                    @Override
                    public void wxItemShare() {
//                        showSuccessToast("微信分享成功");
                        ShareUtils.wxShare(ShareSDK.getPlatform(Wechat.NAME), homeToWebViewInfo);
                    }

                    @Override
                    public void pyqItemShare() {
//                        showSuccessToast("朋友圈分享成功");
                        ShareUtils.wxShare(ShareSDK.getPlatform(Wechat.NAME), homeToWebViewInfo);
                    }
                });
                break;
        }
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
        if (url != null) {
            Intent intent = new Intent(this, LookImageActivity.class);
            intent.putExtra("image", url);
            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeBaseWebViewEvents(BaseWebViewEvents baseWebViewEvents) {
        switch (baseWebViewEvents.getWhat()) {
            case Constans.COLLECT:
                ivFollow.setImageResource(R.drawable.ic_heart_follow);
                showSuccessToast("收藏成功");
                break;
            case Constans.COLLECTERROR:
                showErrorToast(baseWebViewEvents.getData().toString());
                break;
            /*case Constans.RELOGIN:
                startActivity(new Intent(this, LoginActivity.class));
                AppManager.getAppManager().finishActivity(this);
                break;*/
        }
    }

    @Override
    protected void onDestroy() {
        webView.getH5JsInterface().unRegisterListener();
        AppManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);
        if (webView != null) {
//            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
