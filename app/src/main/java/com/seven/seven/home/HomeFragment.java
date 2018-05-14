package com.seven.seven.home;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.base.BaseFragment;
import com.seven.seven.common.base.BasePresenter;
import com.seven.seven.common.event.NetWorkChangeEvent;
import com.seven.seven.common.utils.ToastUtils;
import com.seven.seven.common.view.ErrorLayoutView;
import com.seven.seven.common.view.NumberPlayTextView;
import com.seven.seven.common.view.RollNumberTextView;
import com.seven.seven.home.contract.HomeContract;
import com.seven.seven.home.presenter.HomePresenter;
import com.seven.seven.mvp.view.TestActivity3;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeFragment extends BaseRecyclerFragment<HomeContract.HomePresenter, HomeContract.IHomeModle> implements HomeContract.IHomeView {

    private ErrorLayoutView errorLayoutView;
    private RecyclerView recyclerView;
    private HomePresenter homePresenter;
    private TextView textView;
    private NumberPlayTextView numberPlayTextView;
    private RollNumberTextView rollNumberTextView;
    //    private List<HomeArticleInfo> homeArticleInfoList = new ArrayList<>();
//    private HomeAdapter homeAdapter;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return homePresenter = new HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        textView = rootView.findViewById(R.id.text);
//        textView.setText("我是A");
        recyclerView = rootView.findViewById(R.id.recycler);
        errorLayoutView = rootView.findViewById(R.id.error);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        playNumber(100000);
        numberPlayTextView = rootView.findViewById(R.id.play_view);
        rollNumberTextView = rootView.findViewById(R.id.roll_view);
        rollNumberTextView.startAnimator(1000000,999988989);
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
//                startActivity(new Intent(getContext(), TestActivity3.class));
                numberPlayTextView.setMText(988888888, 3000);
                rollNumberTextView.startAnimator(1,999988989);
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

    }
}
