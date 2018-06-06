package com.seven.seven.user;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.PreferencesUtils;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.home.HomeNewsDetailActivity;
import com.seven.seven.home.model.HomeToWebViewInfo;
import com.seven.seven.user.adapter.CollectAdapter;
import com.seven.seven.user.contract.CollectContract;
import com.seven.seven.user.userevent.CollectEvent;
import com.seven.seven.user.model.CollectInfo;
import com.seven.seven.user.model.CollectListInfos;
import com.seven.seven.user.presenter.CollectPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class CollectListActivity extends BaseActivity implements CollectContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView recyclerView;
    private CollectPresenter collectPresenter;
    private CollectAdapter collectAdapter;
    private boolean isFirst = true;
    private static int PAGE = 0;
    private List<CollectInfo> collectInfoList = new ArrayList<>();
    private boolean enableDelete = true;
    private CollectInfo collectInfo;
    private int position;
    private ImageView ivBack;

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        collectPresenter = new CollectPresenter(this, this);
        View view = findViewById(R.id.space);
        view.setBackground(mActivity.getResources().getDrawable(R.drawable.home_toolbar_bg));
        StatusBarUtil.setFadeStatusBarHeight(mActivity, view);
        ivBack = findViewById(R.id.iv_back);
        recyclerView = findViewById(R.id.rv_collect);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initRecycler();
    }

    /*private void initRecycler() {
        collectAdapter = new CollectAdapter(R.layout.recycler_item_collect);
        recyclerView.setAdapter(collectAdapter);
    }*/

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeCollectEvents(CollectEvent collectEvent) {
        switch (collectEvent.getWhat()) {
            case Constans.COLLECTLIST:
                CollectListInfos collectListInfosList = (CollectListInfos) collectEvent.getData();
                if (collectListInfosList.getDatas().size() != 0) {
                    if (isFirst) {
                        collectInfoList.addAll(collectListInfosList.getDatas());
                        collectAdapter.setNewData(collectInfoList);
                        isFirst = false;
                    } else {
                        collectAdapter.addData(collectListInfosList.getDatas());
                    }
                } else {
                    collectAdapter.loadMoreEnd();
                }

                break;
            case Constans.COLLECTLISTERROR:
                collectAdapter.loadMoreFail();
                showErrorToast(collectEvent.getData().toString());
                break;
            case Constans.DELETECOLLECT:
                showSuccessToast("删除成功");
                enableDelete = true;
                break;
            case Constans.DELETECOLLECTERROR:
                showErrorToast("删除失败");
                if (collectInfo != null) {
                    collectAdapter.addData(position, collectInfo);
                    collectAdapter.notifyDataSetChanged();
                }
                enableDelete = false;
                break;
        }
    }

    private void initRecycler() {
        collectAdapter = new CollectAdapter(R.layout.recycler_item_collect, collectInfoList);
        recyclerView.setAdapter(collectAdapter);
        collectAdapter.setOnLoadMoreListener(this, recyclerView);
        collectAdapter.disableLoadMoreIfNotFullPage();
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(collectAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        itemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);

        collectAdapter.enableSwipeItem();
        collectAdapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("onItemSwipeStart", "执行了");
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("clearView", "执行了");

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("onItemSwiped", "执行了");
                collectInfo = collectAdapter.getItem(pos);
                position = pos;
                if (collectInfo != null) {
                    collectPresenter.deleteCollect(collectInfo.getId());
                }
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(CollectListActivity.this, R.color.red));
                Log.d("onItemSwipeMoving", "执行了");
            }
        });
        collectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CollectInfo collectInfo = (CollectInfo) adapter.getItem(position);
                HomeToWebViewInfo homeToWebViewInfo = new HomeToWebViewInfo();
                homeToWebViewInfo.collect = collectInfo.isCollect();
                homeToWebViewInfo.h5Url = collectInfo.getLink();
                homeToWebViewInfo.id = collectInfo.getId();
                homeToWebViewInfo.imgUrl = collectInfo.getEnvelopePic();
                homeToWebViewInfo.title = collectInfo.getTitle();
                Intent intent = new Intent(mActivity, HomeNewsDetailActivity.class);
                intent.putExtra("newsInfo", homeToWebViewInfo);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
/*
        PreferencesUtils.putString(this, Constans.COOKIE_PREF, null);
*/
        PAGE = 0;
        collectPresenter.getCollectList(PAGE);
    }

    @Override
    protected void setLisenter() {
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                AppManager.getAppManager().finishActivity(this);
                break;
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_collect_list;
    }

    @Override
    protected boolean isNeedTranslateBar() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMoreRequested() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ++PAGE;
                collectPresenter.getCollectList(PAGE);
                collectAdapter.loadMoreComplete();
            }
        }, 1000);
    }
}
