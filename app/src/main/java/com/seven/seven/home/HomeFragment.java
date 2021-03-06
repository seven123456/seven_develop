package com.seven.seven.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import com.seven.seven.home.model.HomeBannerInfos;
import com.seven.seven.home.events.HomeEvents;
import com.seven.seven.home.model.HomeNewsInfos;
import com.seven.seven.home.model.HomeToWebViewInfo;
import com.seven.seven.home.presenter.HomePresenter;
import com.seven.seven.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, BaseQuickAdapter.RequestLoadMoreListener {

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
    private boolean isFirst;
    private static int CURPAGE = 1;
    private static int PAGE_COUNT = 0;
    private boolean isRefresh = false;
    private BaseHomeTitleBar homeTitleBar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homePresenter = new HomePresenter(this, (MainActivity) getActivity());
        EventBus.getDefault().register(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
//        appBarLayout = rootView.findViewById(R.id.appbar);
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) appBarLayout.getLayoutParams();
//        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getActivity());
//        appBarLayout.setTitle("首页新闻");
        homeTitleBar = rootView.findViewById(R.id.in_home_title);
        swipeRefreshLayout = rootView.findViewById(R.id.swf_layout);
        recyclerView = rootView.findViewById(R.id.recycler);
        errorLayoutView = rootView.findViewById(R.id.error);
        errorLayoutView.setVisibility(View.VISIBLE);
        errorLayoutView.playAnimation();
        initRecyclerView();
    }

    /*
    * 初始化recycler并且初始化headview添加到recycler里面
    * */
    private void initRecyclerView() {
        recyclerView.setLayoutManager(linearLayoutManager);
        homeCommonAdapter = new HomeCommonAdapter(R.layout.recycler_item_home_news, newsInfosList, getContext());
        initHeadView();
        homeCommonAdapter.setOnLoadMoreListener(this, recyclerView);
        homeCommonAdapter.disableLoadMoreIfNotFullPage();
        homeCommonAdapter.addHeaderView(headView);
        recyclerView.setAdapter(homeCommonAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            Drawable drawable = getContext().getResources().getDrawable(R.drawable.home_toolbar_bg);

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (isFirst) {
//                int toolbarHeight = appBarLayout.getMeasuredHeight();
                int toolbarHeight = getResources().getDimensionPixelSize(R.dimen.main_title_bar_height);
                int scollyHeight = recyclerView.computeVerticalScrollOffset();
                if (scollyHeight >= (toolbarHeight * 2)) {
                        /*此时toolbar和状态栏完全显示红色*/
//                    appBarLayout.setVisibility(View.VISIBLE);
//                    drawable.setAlpha(255);
//                    appBarLayout.setBackground(drawable);
                    homeTitleBar.setBgAlpha(1);
                } else if (scollyHeight >= toolbarHeight) {
                        /*
                        * 渐变过程
                        * */
//                    appBarLayout.setVisibility(View.VISIBLE);
//                    drawable.setAlpha((int) (255 * ((scollyHeight - toolbarHeight) / (toolbarHeight * 1.5F))));
//                    appBarLayout.setBackground(drawable);
                    homeTitleBar.setBgAlpha((scollyHeight - toolbarHeight) / (toolbarHeight * 2F));
                } else {
                        /*
                        * 状态栏透明
                        * */
//                    appBarLayout.setVisibility(View.GONE);
                    homeTitleBar.setBgAlpha(0);
                }
//                } else {
//                    isFirst = true;
//                }
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
                homeToWebViewInfo.id = newsInfos.getId();
                homeToWebViewInfo.collect = newsInfos.isCollect();
                Intent intent = new Intent(getContext(), HomeNewsDetailActivity.class);
                intent.putExtra("newsInfo", homeToWebViewInfo);
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
                homeCommonAdapter.setEnableLoadMore(false);
            }
        });
        errorLayoutView.setErrorOnclickListenter(new ErrorLayoutView.onErrorLayoutListenter() {
            @Override
            public void replayLoading() {
                errorLayoutView.playAnimation();
                homePresenter.getHomeBanner();
            }
        });
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
                break;
            case Constans.NET_WORK_DISABLED://没网
                errorLayoutView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = true)
    public void disposeHomeFragmentData(HomeEvents homeEvents) {
        switch (homeEvents.getWhat()) {
            case Constans.HOMEDATA:
                HomeNewsInfos homeNewsInfos = (HomeNewsInfos) homeEvents.getData();
                newsInfosList = homeNewsInfos.getDatas();
                if (newsInfosList != null) {
                    errorLayoutView.hide();
                }
                if (!isRefresh) {
                    homeCommonAdapter.setNewData(newsInfosList);
                    isRefresh = true;
                    homeCommonAdapter.setEnableLoadMore(true);
                } else {
                    if (newsInfosList != null) {
                        homeCommonAdapter.addData(newsInfosList);
                    } else {
                        homeCommonAdapter.loadMoreEnd(true);
                    }
                }
                PAGE_COUNT = homeNewsInfos.getPageCount();
                break;
            case Constans.HOMEBANNER:
                homeBannerInfos = (List<HomeBannerInfos>) homeEvents.getData();
                initRecyclerHeadView(homeBannerInfos);
                if (homeBannerInfos != null) {
                    errorLayoutView.hide();
                }

                break;
            case Constans.HOMEERROR:
                showErrorToast((String) homeEvents.getData());
                errorLayoutView.showErrorView();
                break;
            case Constans.HOMEDASUCCESS:
                errorLayoutView.hide();
                break;
        }
//        }
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
        /*switch (v.getId()) {
            case R.id.error:

                break;

        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /*
    * 获取recycler第一个可见item高度
    * */
    public int getScollyY() {
        int position = linearLayoutManager.findFirstVisibleItemPosition() + 1;
        View firstVisibleChildView = linearLayoutManager.findViewByPosition(position);
        int firstHeight = firstVisibleChildView.getHeight();
        return (position) * firstHeight - firstVisibleChildView.getTop();
    }

    @Override
    public void onLoadMoreRequested() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (CURPAGE <= PAGE_COUNT) {
                    CURPAGE++;
                    homePresenter.getMoreHomeData(CURPAGE);
                    homeCommonAdapter.loadMoreComplete();
                } else {
                    homeCommonAdapter.loadMoreEnd(true);
                }
            }
        }, 1000);
    }
}
