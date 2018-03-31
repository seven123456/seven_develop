package com.seven.seven.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.base.BaseFragment;
import com.seven.seven.mvp.view.TestActivity3;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeFragment extends BaseFragment {

    private Observable<List<Infos>> observable;
    private TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment;
    }

    @Override
    protected void initView() {
        textView = rootView.findViewById(R.id.text);
        textView.setText("我是A");

    }

    @Override
    protected void setLisenter() {
        textView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                startActivity(new Intent(getContext(), TestActivity3.class));
                break;
        }
    }

}
