package com.seven.seven.gank;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseFragment;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.view.ErrorLayoutView;
import com.seven.seven.gank.adapter.GankAdapter;
import com.seven.seven.gank.contract.GankContract;
import com.seven.seven.gank.events.GankEvents;
import com.seven.seven.gank.model.GankIoWelfareBean;
import com.seven.seven.gank.presenter.GankPresenter;
import com.seven.seven.search.LookImageActivity;
import com.seven.seven.ui.MainActivity;
import com.seven.seven.ui.MyApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class GankFragment extends BaseFragment implements GankContract.View {

    private RecyclerView recyclerView;
    private GankAdapter gankAdapter;
    private GankPresenter gankPresenter;
    private static int PAGE = 1;
    private ErrorLayoutView inError;

    @Override
    protected void initData() {
        gankPresenter.getGankList();
    }

    @Override
    protected void setLisenter() {
        inError.setErrorOnclickListenter(new ErrorLayoutView.onErrorLayoutListenter() {
            @Override
            public void replayLoading() {
                inError.playAnimation();
                gankPresenter.getGankList();
            }
        });
    }

    @Override
    protected void widgetClick(View v) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeGankEvents(GankEvents gankEvents) {
        switch (gankEvents.getWhat()) {
            case Constans.GANKIO:
                inError.hide();
                initRecycler((List<GankIoWelfareBean>) gankEvents.getData());
                break;
            case Constans.GANKIOERROR:
                inError.showErrorView();
                showSuccessToast(gankEvents.getData().toString());
                break;
        }
    }

    private void initRecycler(List<GankIoWelfareBean> data) {
        gankAdapter = new GankAdapter(R.layout.recycler_item_gank, data);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(gankAdapter);
        gankAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GankIoWelfareBean gankIoWelfareBean = (GankIoWelfareBean) adapter.getItem(position);
                Intent intent = new Intent(getContext(), LookImageActivity.class);
                intent.putExtra("image", gankIoWelfareBean.getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        gankPresenter = new GankPresenter(this, (MainActivity) getActivity());
        inError = rootView.findViewById(R.id.in_error);
        inError.setVisibility(View.VISIBLE);
        inError.playAnimation();
        recyclerView = rootView.findViewById(R.id.recycler);
        gankAdapter = new GankAdapter(R.layout.recycler_item_gank);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gankAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
