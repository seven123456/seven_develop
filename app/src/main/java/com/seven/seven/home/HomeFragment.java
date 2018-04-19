package com.seven.seven.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.base.BaseFragment;
import com.seven.seven.common.base.BasePresenter;
import com.seven.seven.common.event.NetWorkChangeEvent;
import com.seven.seven.common.view.ErrorLayoutView;
import com.seven.seven.home.adapter.HomeAdapter;
import com.seven.seven.home.contract.HomeContract;
import com.seven.seven.home.modle.HomeArticleInfo;
import com.seven.seven.home.presenter.HomePresenter;
import com.seven.seven.mvp.view.TestActivity3;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeFragment extends BaseRecyclerFragment<HomeContract.HomePresenter, HomeContract.IHomeModle> implements HomeContract.IHomeView {

    private ErrorLayoutView errorLayoutView;
    private RecyclerView recyclerView;
    private HomePresenter homePresenter;
    private List<HomeArticleInfo> homeArticleInfoList = new ArrayList<>();
    private HomeAdapter homeAdapter;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        Log.d("init", "initPresenter");
        return homePresenter = new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        Log.d("init", "getLayoutId");
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void initView() {
        Log.d("init", "initView");
        EventBus.getDefault().register(this);
        recyclerView = rootView.findViewById(R.id.recycler);
        errorLayoutView = rootView.findViewById(R.id.error);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(R.layout.item_home_layout, homeArticleInfoList);
        recyclerView.setAdapter(homeAdapter);
        WindowManager windowManager =getActivity().getWindowManager();

    }

    @Override
    protected void setLisenter() {
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = true)
    public void disposeNetWorkChange(NetWorkChangeEvent netWorkChangeEvent) {
        switch (netWorkChangeEvent.getWhat()) {
            case NetWorkChangeEvent.NET_WORK_AVAILABLE://有网
                errorLayoutView.setVisibility(View.GONE);
               /* if (mPresenter != null) {
                    mPresenter.loadTestList();
                }*/
                break;
            case NetWorkChangeEvent.NET_WORK_DISABLED://没网
                errorLayoutView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                startActivity(new Intent(getContext(), TestActivity3.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onErrorViewOnclick(View v) {

    }

    @Override
    protected void ShowLoadingView() {
        Log.d("init", "ShowLoadingView");
      /*  homeAdapter.setEmptyView(errorView);*/

    }
}
