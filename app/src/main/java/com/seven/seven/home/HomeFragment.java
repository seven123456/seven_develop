package com.seven.seven.home;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.base.codereview.BaseFragment;
import com.seven.seven.common.base.codereview.BasePresenter;
import com.seven.seven.common.event.NetWorkChangeEvent;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.GsonTools;
import com.seven.seven.common.view.ErrorLayoutView;
import com.seven.seven.common.view.NumberPlayTextView;
import com.seven.seven.common.view.RollNumberTextView;
import com.seven.seven.home.contract.HomeContract;
import com.seven.seven.home.events.HomeEvents;
import com.seven.seven.home.presenter.HomePresenter;
import com.seven.seven.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private ErrorLayoutView errorLayoutView;
    private RecyclerView recyclerView;
    private HomePresenter homePresenter;
    private TextView textView;
    private NumberPlayTextView numberPlayTextView;
    private RollNumberTextView rollNumberTextView;
    private SwipeRefreshLayout swipeRefreshLayout;
    //    private List<HomeArticleInfo> homeArticleInfoList = new ArrayList<>();
//    private HomeAdapter homeAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void initView() {
        homePresenter = new HomePresenter(this, (MainActivity) getActivity());
        EventBus.getDefault().register(this);
        textView = rootView.findViewById(R.id.text);
        swipeRefreshLayout = rootView.findViewById(R.id.swf_layout);
//        textView.setText("我是A");
        recyclerView = rootView.findViewById(R.id.recycler);
        errorLayoutView = rootView.findViewById(R.id.error);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
//        playNumber(100000);
        numberPlayTextView = rootView.findViewById(R.id.play_view);
        rollNumberTextView = rootView.findViewById(R.id.roll_view);
        rollNumberTextView.startAnimator(1000000, 123456789);
    }

    private void playNumber(final int value) {
        ValueAnimator valueAnimator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            valueAnimator = ValueAnimator.ofInt(0, value);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        valueAnimator.setDuration(8000);
        valueAnimator.start();
    }

    @Override
    protected void setLisenter() {
        textView.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getHomeData();
            }
        });
    }

    @Override
    protected void initData() {
        homePresenter.getHomeData();
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
        switch (homeEvents.getWhat()) {
            case Constans.HOMEDATA:
                if (homeEvents.getData() != null) {
//                    Infos infos = (Infos) homeEvents.getData();
//                    showSuccessToast(GsonTools.createGsonString(infos));
                }
                break;
            case Constans.HOMEDATAFIAL:
                break;
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.text:
//                startActivity(new Intent(getContext(), TestActivity3.class));
                numberPlayTextView.setMText(988888888, 3000);
                rollNumberTextView.startAnimator(987654321, 123456789);
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void showHomeData() {

    }
}
