package com.seven.seven.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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


    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void initView() {
        homePresenter = new HomePresenter(this, (MainActivity) getActivity());
        StatusBarUtil.setTranslates(getActivity(), true);
        EventBus.getDefault().register(this);
        Toolbar appBarLayout = rootView.findViewById(R.id.appbar);
        appBarLayout.setTitle("首页新闻");
        swipeRefreshLayout = rootView.findViewById(R.id.swf_layout);
        recyclerView = rootView.findViewById(R.id.recycler);
        errorLayoutView = rootView.findViewById(R.id.error);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeCommonAdapter = new HomeCommonAdapter(R.layout.recycler_item_home_news, newsInfosList, getContext());
        recyclerView.setAdapter(homeCommonAdapter);
        homeCommonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeNewsInfos.NewsInfos newsInfos = (HomeNewsInfos.NewsInfos) adapter.getItem(position);

                
            }
        });
        BannerLayout bannerLayout = rootView.findViewById(R.id.bl_banner);
        bannerLayout.setAutoPlaying(true);
        bannerViewAdapter = new BannerViewAdapter(R.layout.recycler_item_banner, homeBannerInfos, getContext());
        bannerLayout.setAdapter(bannerViewAdapter);
        bannerViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showSuccessToast("点击了" + position);
            }
        });
    }

    @Override
    protected void setLisenter() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getHomeData();
                homePresenter.getHomeBanner();
            }
        });
    }

    @Override
    protected void initData() {
        homePresenter.getHomeData();
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
                    showSuccessToast("成功了");
                    break;
                case Constans.HOMEBANNER:
                    homeBannerInfos = (List<HomeBannerInfos>) homeEvents.getData();
                    bannerViewAdapter.setNewData(homeBannerInfos);
//                    showSuccessToast(homeBannerInfos.toString());
                    break;
                case Constans.HOMEDATAFIAL:
                    showErrorToast((String) homeEvents.getData());
                    break;
            }
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.text:
//                startActivity(new Intent(getContext(), TestActivity3.class));
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
