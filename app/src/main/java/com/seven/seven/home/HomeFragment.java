package com.seven.seven.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseFragment;
import com.seven.seven.common.event.NetWorkChangeEvent;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.common.view.ErrorLayoutView;
import com.seven.seven.home.adapter.BannerViewAdapter;
import com.seven.seven.home.adapter.HomeCommonAdapter;
import com.seven.seven.home.bannerview.BannerLayout;
import com.seven.seven.home.contract.HomeContract;
import com.seven.seven.home.events.HomeBannerInfos;
import com.seven.seven.home.events.HomeEvents;
import com.seven.seven.home.model.HomeNewsInfos;
import com.seven.seven.home.model.HomeToWebViewInfo;
import com.seven.seven.home.presenter.HomePresenter;
import com.seven.seven.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private ErrorLayoutView errorLayoutView;
    private RecyclerView recyclerView;
    private HomePresenter homePresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<HomeNewsInfos.NewsInfos> newsInfosList;
    private HomeCommonAdapter homeCommonAdapter;
    private List<HomeBannerInfos> homeBannerInfos;
    private BannerViewAdapter bannerViewAdapter;
    private BannerLayout bannerLayout;
    private View headView;
    private Toolbar appBarLayout;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void initView() {
        homePresenter = new HomePresenter(this, (MainActivity) getActivity());
        StatusBarUtil.setTranslate(getActivity(), true);
        EventBus.getDefault().register(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        appBarLayout = rootView.findViewById(R.id.appbar);
//        appBarLayout.setTitle("首页新闻");
        swipeRefreshLayout = rootView.findViewById(R.id.swf_layout);
        recyclerView = rootView.findViewById(R.id.recycler);
        errorLayoutView = rootView.findViewById(R.id.error);
        errorLayoutView.setVisibility(View.VISIBLE);
        initRecyclerView();
    }

    /*
    * 初始化recycler并且初始化headview添加到recycler里面
    * */
    private void initRecyclerView() {
        recyclerView.setLayoutManager(linearLayoutManager);
        homeCommonAdapter = new HomeCommonAdapter(R.layout.recycler_item_home_news, newsInfosList, getContext());
        initHeadView();
        homeCommonAdapter.addHeaderView(headView);
        recyclerView.setAdapter(homeCommonAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.home_toolbar_bg);

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (newsInfosList != null) {
                    int toolbarHeight = appBarLayout.getMeasuredHeight();
                    int scollyHeight = recyclerView.computeVerticalScrollOffset();
                    if (scollyHeight >= (toolbarHeight * 2)) {
                        appBarLayout.setVisibility(View.VISIBLE);
                        drawable.setAlpha(255);
                        appBarLayout.setBackground(drawable);
//                        StatusBarUtil.customTranslate(getActivity(), getContext().getResources().getColor(R.color.red));
                    } else if (scollyHeight >= toolbarHeight) {
                        appBarLayout.setVisibility(View.VISIBLE);
                        drawable.setAlpha((int) (255 * ((scollyHeight - toolbarHeight) / (toolbarHeight * 1.5F))));
                        appBarLayout.setBackground(drawable);
                    } else {
                        appBarLayout.setVisibility(View.GONE);
                    }
                    /*if (getScollyY() > 0) {
                        appBarLayout.setVisibility(View.VISIBLE);
                        if (getScollyY() > 30) {
                            appBarLayout.setBackgroundColor(getContext().getResources().getColor(R.color.red));
                        } else {
                            appBarLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
                        }
                    } else {
                        appBarLayout.setVisibility(View.GONE);
                    }*/
                    Log.d("homefragment", "====" + recyclerView.computeVerticalScrollOffset() + "TOOL高度==" + appBarLayout.getMeasuredHeight());

                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        homeCommonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeNewsInfos.NewsInfos newsInfos = (HomeNewsInfos.NewsInfos) adapter.getItem(position);
                HomeToWebViewInfo homeToWebViewInfo = new HomeToWebViewInfo();
                homeToWebViewInfo.h5Url = newsInfos.getLink();
                homeToWebViewInfo.imgUrl = newsInfos.getEnvelopePic();
                homeToWebViewInfo.title = newsInfos.getTitle();
                Intent intent = new Intent(getContext(), HomeNewsDetailActivity.class);
                intent.putExtra("newsInfo", homeToWebViewInfo);
                ImageView imageView = view.findViewById(R.id.iv_right);
                /*
                * item 里面的img设置一个属性，相对应页面的img里面也必须设置
                * getResources().getString(R.string.transition_news_img)
                * */
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                        ((Activity) getContext(), view.findViewById(R.id.iv_right), getResources().getString(R.string.transition_news_img));
                ActivityCompat.startActivity(getContext(), intent, options.toBundle());
            }
        });

    }

    private void setAlphaAnimation() {

    }

    @Override
    protected void setLisenter() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getHomeBanner();
            }
        });
        errorLayoutView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        homePresenter.getHomeBanner();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = true)
    public void disposeNetWorkChange(NetWorkChangeEvent netWorkChangeEvent) {
        switch (netWorkChangeEvent.getWhat()) {
            case Constans.NET_WORK_AVAILABLE://有网
                errorLayoutView.setVisibility(View.GONE);
               /* if (mPresenter != null) {
                    mPresenter.loadTestList();
                }*/
                break;
            case Constans.NET_WORK_DISABLED://没网
                errorLayoutView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = true)
    public void disposeHomeFragmentData(HomeEvents homeEvents) {
        if (homeEvents.getData() != null) {
            switch (homeEvents.getWhat()) {
                case Constans.HOMEDATA:
                    HomeNewsInfos homeNewsInfos = (HomeNewsInfos) homeEvents.getData();
                    newsInfosList = homeNewsInfos.getDatas();
                    homeCommonAdapter.setNewData(newsInfosList);
                    break;
                case Constans.HOMEBANNER:
                    homeBannerInfos = (List<HomeBannerInfos>) homeEvents.getData();
                    initRecyclerHeadView(homeBannerInfos);
//                    bannerViewAdapter.setNewData(homeBannerInfos);
//                    showSuccessToast(homeBannerInfos.toString());
                    break;
                case Constans.HOMEDATAFIAL:
                    showErrorToast((String) homeEvents.getData());
                    break;
                case Constans.HOMEDASUCCESS:
                    showSuccessToast("俩次请求数据成功");
                    errorLayoutView.setVisibility(View.GONE);
                    break;
            }
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initRecyclerHeadView(List<HomeBannerInfos> homeBannerInfos) {
        bannerViewAdapter = new BannerViewAdapter(R.layout.recycler_item_banner, homeBannerInfos, getContext());
        bannerLayout.setAdapter(bannerViewAdapter);
        bannerViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeBannerInfos homeBannerInfos = (HomeBannerInfos) adapter.getItem(position);
                HomeToWebViewInfo homeToWebViewInfo = new HomeToWebViewInfo();
                Intent intent = new Intent(getContext(), HomeNewsDetailActivity.class);
                homeToWebViewInfo.title = homeBannerInfos.getTitle();
                homeToWebViewInfo.imgUrl = homeBannerInfos.getImagePath();
                homeToWebViewInfo.h5Url = homeBannerInfos.getUrl();
                intent.putExtra("newsInfo", homeToWebViewInfo);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                        ((Activity) getContext(), view.findViewById(R.id.iv_banner_image), getResources().getString(R.string.transition_news_img));
                ActivityCompat.startActivity(getContext(), intent, options.toBundle());
            }
        });
    }

    /*
    * 初始化headview，并且初始化一个空list的banneradapter，加载数据成功以后再初始化banneradapter并且绑定bannerview
    * */
    private void initHeadView() {
        if (headView == null) {
            headView = View.inflate(getContext(), R.layout.recycler_head_view, null);
        }
        bannerLayout = headView.findViewById(R.id.bl_banner);
        bannerViewAdapter = new BannerViewAdapter(R.layout.recycler_item_banner, getContext());
        bannerLayout.setAdapter(bannerViewAdapter);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.error:
//                startActivity(new Intent(getContext(), TestActivity3.class));
                homePresenter.getHomeBanner();
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    public int getScollyY() {
        int position = linearLayoutManager.findFirstVisibleItemPosition() + 1;
        View firstVisibleChildView = linearLayoutManager.findViewByPosition(position);
        int firstHeight = firstVisibleChildView.getHeight();
        return (position) * firstHeight - firstVisibleChildView.getTop();
    }
}
