package com.seven.seven.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.home.HomeNewsDetailActivity;
import com.seven.seven.home.model.HomeNewsInfos;
import com.seven.seven.home.model.HomeToWebViewInfo;
import com.seven.seven.search.contract.SearchKContract;
import com.seven.seven.search.events.SearchKEvent;
import com.seven.seven.search.model.SearchListInfos;
import com.seven.seven.search.presenter.SearchKPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by seven
 * on 2018/6/1
 * email:seven2016s@163.com
 */

public class SearchDetailActivity extends BaseActivity implements SearchKContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private ImageView ivBack;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private SearchKPresenter searchKPresenter;
    private EditText searchText;
    private static int PAGE = 0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslate(this, false);
        EventBus.getDefault().register(this);
        searchKPresenter = new SearchKPresenter(this, this);
        View view = findViewById(R.id.view_space);
        StatusBarUtil.setFadeStatusBarHeight(this, view);
        ivBack = findViewById(R.id.iv_back);
        searchText = findViewById(R.id.et_content);
        recyclerView = findViewById(R.id.rv_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initRecycler();
    }

    private void initRecycler() {
        searchAdapter = new SearchAdapter(R.layout.recycler_item_search);
        recyclerView.setAdapter(searchAdapter);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeSearchKEvents(SearchKEvent searchKEvent) {
        switch (searchKEvent.getWhat()) {
            case Constans.SEARCHK:
                SearchListInfos listInfosList = (SearchListInfos) searchKEvent.getData();
                initRecyclerData(listInfosList.getDatas());
                break;
            case Constans.SEARCHKERROR:
                showErrorToast(searchKEvent.getData().toString());
                break;
        }
    }

    private void initRecyclerData(List<SearchListInfos.DatasBean> listInfosList) {
        searchAdapter = new SearchAdapter(R.layout.recycler_item_search, listInfosList);
//        searchAdapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SearchListInfos.DatasBean newsInfos = (SearchListInfos.DatasBean) adapter.getItem(position);
                HomeToWebViewInfo homeToWebViewInfo = new HomeToWebViewInfo();
                homeToWebViewInfo.h5Url = newsInfos.getLink();
                homeToWebViewInfo.imgUrl = newsInfos.getEnvelopePic();
                homeToWebViewInfo.title = newsInfos.getTitle();
                homeToWebViewInfo.id = newsInfos.getId();
                homeToWebViewInfo.collect = newsInfos.isCollect();
                Intent intent = new Intent(mActivity, HomeNewsDetailActivity.class);
                intent.putExtra("newsInfo", homeToWebViewInfo);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    protected void setLisenter() {
        ivBack.setOnClickListener(this);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.d("SearchDetailActivity", "beforeTextChanged==" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d("SearchDetailActivity", "onTextChanged==" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                Log.d("SearchDetailActivity", "afterTextChanged==" + s);
                if (s.length() != 0) {
                    searchKPresenter.searchK(PAGE, s);
                } else {
                    searchAdapter.setNewData(null);
                }
            }
        });
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
        return R.layout.activity_search_detail;
    }

    @Override
    protected boolean isNeedTranslateBar() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchAdapter.setNewData(null);
        EventBus.getDefault().unregister(this);
    }
}
