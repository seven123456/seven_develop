package com.seven.seven.search;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseFragment;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.common.view.FlowLayout;
import com.seven.seven.home.HomeNewsDetailActivity;
import com.seven.seven.home.model.HomeToWebViewInfo;
import com.seven.seven.search.contract.SearchContract;
import com.seven.seven.search.events.HotTagEvent;
import com.seven.seven.search.model.HotTagInfos;
import com.seven.seven.search.model.UsingURLinfos;
import com.seven.seven.search.presenter.HotTagPresenter;
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

public class SearchFragment extends BaseFragment implements SearchContract.View {


    private FlowLayout flowLayout, urlFlowLayout;
    private HotTagPresenter hotTagPresenter;
    private RelativeLayout searchView;
    private long lastTime = 0;

    @Override
    protected void initView() {
        StatusBarUtil.setTranslate(getActivity(), false);
        EventBus.getDefault().register(this);
        hotTagPresenter = new HotTagPresenter(this, (MainActivity) getActivity());
        View viewspace = rootView.findViewById(R.id.view_space);
        StatusBarUtil.setFadeStatusBarHeight(getContext(), viewspace);
        flowLayout = rootView.findViewById(R.id.flow_layout);
        urlFlowLayout = rootView.findViewById(R.id.flow_url_layout);
        searchView = rootView.findViewById(R.id.search_title);
    }

    private void initFlowlayout(final List<HotTagInfos> hotTagInfos) {
        // 循环添加TextView到容器
        for (int i = 0; i < hotTagInfos.size(); i++) {
            final TextView view = new TextView(getContext());
            view.setText(hotTagInfos.get(i).getName());
            view.setTextColor(getResources().getColor(R.color.search_text));
            view.setPadding(5, 5, 5, 5);
            view.setGravity(Gravity.CENTER);
            view.setTextSize(14);
            // 设置点击事件
            final int index = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSuccessToast(hotTagInfos.get(index).getName());
                }
            });

            // 设置彩色背景
            GradientDrawable normalDrawable = new GradientDrawable();
            normalDrawable.setColor(getResources().getColor(R.color.seven));

           /* // 设置按下的灰色背景
            GradientDrawable pressedDrawable = new GradientDrawable();
            pressedDrawable.setShape(GradientDrawable.RECTANGLE);
            pressedDrawable.setColor(Color.GRAY);*/

            // 背景选择器
            StateListDrawable stateDrawable = new StateListDrawable();
//            stateDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
            stateDrawable.addState(new int[]{}, normalDrawable);
            // 设置背景选择器到TextView上
            view.setBackground(stateDrawable);
            flowLayout.addView(view);
        }
    }

    @Override
    protected void initData() {
        hotTagPresenter.getHotTag();
        hotTagPresenter.getUsingURL();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeSearchEvents(HotTagEvent hotTagEvent) {
        switch (hotTagEvent.getWhat()) {
            case Constans.HOTTAG:
                if (hotTagEvent.getData() != null) {
                    initFlowlayout((List<HotTagInfos>) hotTagEvent.getData());
                }
                break;
            case Constans.HOTTAGERROR:
                showErrorToast(hotTagEvent.getData().toString());
                break;
            case Constans.USINGURL:
                if (hotTagEvent.getData() != null) {
                    initURLFlowlayout((List<UsingURLinfos>) hotTagEvent.getData());
                }
                break;
            case Constans.USINGURLERROR:
                showErrorToast(hotTagEvent.getData().toString());
                break;
        }
    }

    private void initURLFlowlayout(final List<UsingURLinfos> usingURLinfos) {
        // 循环添加TextView到容器
        for (int i = 0; i < usingURLinfos.size(); i++) {
            final TextView view = new TextView(getContext());
            view.setText(usingURLinfos.get(i).getName());
            view.setTextColor(getResources().getColor(R.color.search_text));
            view.setPadding(5, 5, 5, 5);
            view.setGravity(Gravity.CENTER);
            view.setTextSize(14);
            // 设置点击事件
            final int index = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long currTime = System.currentTimeMillis();
                    if (currTime - lastTime > 1000) {
                        Log.d("onClick", "点击了");
//                    showSuccessToast(usingURLinfos.get(index).getName());
                        Intent intent = new Intent(getContext(), HomeNewsDetailActivity.class);
                        HomeToWebViewInfo homeToWebViewInfo = new HomeToWebViewInfo();
                        homeToWebViewInfo.h5Url = usingURLinfos.get(index).getLink();
                        homeToWebViewInfo.id = usingURLinfos.get(index).getId();
                        homeToWebViewInfo.imgUrl = usingURLinfos.get(index).getIcon();
                        homeToWebViewInfo.collect = true;
                        intent.putExtra("newsInfo", homeToWebViewInfo);
                        startActivity(intent);
                    }
                    lastTime = currTime;
                }
            });

            // 设置彩色背景
            GradientDrawable normalDrawable = new GradientDrawable();
            normalDrawable.setColor(getResources().getColor(R.color.seven));

           /* // 设置按下的灰色背景
            GradientDrawable pressedDrawable = new GradientDrawable();
            pressedDrawable.setShape(GradientDrawable.RECTANGLE);
            pressedDrawable.setColor(Color.GRAY);*/

            // 背景选择器
            StateListDrawable stateDrawable = new StateListDrawable();
//            stateDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
            stateDrawable.addState(new int[]{}, normalDrawable);
            // 设置背景选择器到TextView上
            view.setBackground(stateDrawable);
            urlFlowLayout.addView(view);
        }
    }


    @Override
    protected void setLisenter() {
        searchView.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.search_title:
                startActivity(new Intent(getContext(), SearchDetailActivity.class));
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
