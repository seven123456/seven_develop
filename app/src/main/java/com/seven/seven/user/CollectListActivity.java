package com.seven.seven.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.user.adapter.CollectAdapter;
import com.seven.seven.user.contract.CollectContract;
import com.seven.seven.user.events.CollectEvent;
import com.seven.seven.user.model.CollectInfo;
import com.seven.seven.user.model.CollectListInfos;
import com.seven.seven.user.presenter.CollectPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class CollectListActivity extends BaseActivity implements CollectContract.View {

    private RecyclerView recyclerView;
    private CollectPresenter collectPresenter;
    private CollectAdapter collectAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        collectPresenter = new CollectPresenter(this, this);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        TextView toolbarTitle = findViewById(R.id.toolbar_title);
//        toolbarTitle.setText("收集页面");
       /* RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(this);*/
        toolbar.setBackground(mActivity.getResources().getDrawable(R.drawable.home_toolbar_bg));
        initTitleBar(toolbar, "收集页面");
        recyclerView = findViewById(R.id.rv_collect);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initRecycler();
    }

    private void initRecycler() {
        collectAdapter = new CollectAdapter(R.layout.recycler_item_collect);
        recyclerView.setAdapter(collectAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeCollectEvents(CollectEvent collectEvent) {
        switch (collectEvent.getWhat()) {
            case Constans.COLLECTLIST:
                CollectListInfos collectListInfosList = (CollectListInfos) collectEvent.getData();
                if (collectListInfosList != null) {
                    initRecyclerData(collectListInfosList.getDatas());
                }
                break;
            case Constans.COLLECTLISTERROR:
                showErrorToast(collectEvent.getData().toString());
                break;
        }

    }

    private void initRecyclerData(List<CollectInfo> collectListInfosList) {
        collectAdapter = new CollectAdapter(R.layout.recycler_item_collect, collectListInfosList);
        recyclerView.setAdapter(collectAdapter);
        collectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showSuccessToast("点击了" + position + "个");
            }
        });
    }

    @Override
    protected void initData() {
        collectPresenter.getCollectList(0);
    }

    @Override
    protected void setLisenter() {

    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_collect_list;
    }

    @Override
    protected boolean isNeedTranslateBar() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
