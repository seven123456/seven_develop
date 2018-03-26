package com.seven.seven.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.seven.seven.R;
import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.event.NetWorkChangeEvent;
import com.seven.seven.common.utils.ToastUtils;
import com.seven.seven.common.view.MarqueeView;
import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.mvp.presenter.TestPresenter;
import com.seven.seven.common.base.BaseMvpActivity;
import com.seven.seven.common.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestActivity3 extends BaseMvpActivity<TestContract.TestPresenter, TestContract.ITestModle> implements TestContract.ITestView {

    private TestPresenter mPresenter;
    private MarqueeView marqueeView;
    private List<String> list;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout errorLayout;

    @NonNull
    @Override
    public BasePresenter initPresenter() {//在这里发起
        mPresenter = new TestPresenter(this);
        return mPresenter;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mPresenter.loadTestList();//v给p层发送业务处理，举个例子
        swipeRefreshLayout = findViewById(R.id.swf);
        errorLayout = findViewById(R.id.error);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                ToastUtils.showToast("下拉咯,跑马灯有没有黑边呢");
            }
        });
        list = new ArrayList<>();
//        String a = "第1个";
//        "第2个", "第3个", "第4个", "第5个", "第6个", "第7个";
        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "个");
        }
    /*    marqueeView = findViewById(R.id.marquee_view);
        marqueeView.setText(list);*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0, sticky = true)
    public void disposeNetWorkChange(NetWorkChangeEvent netWorkChangeEvent) {
        switch (netWorkChangeEvent.getWhat()) {
            case NetWorkChangeEvent.NET_WORK_AVAILABLE://有网
                errorLayout.setVisibility(View.GONE);
                if (mPresenter != null) {
                    mPresenter.loadTestList();
                }
                break;
            case NetWorkChangeEvent.NET_WORK_DISABLED://没网
                errorLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.layout_b_fragment;
    }

    @Override
    public void showTestData(Infos testInfoList) {
//        for (int i = 0; i < testInfoList.size(); i++) {
        ToastUtils.showToast(testInfoList.getData().toString());
//        }
    }

    @Override
    public void showNetworkError() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNoMoreData() {

    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
        ToastUtils.showToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
