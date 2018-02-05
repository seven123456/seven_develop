package com.seven.seven.ui.base.fragment;

import android.widget.TextView;

import com.seven.seven.R;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class CFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView textView = rootView.findViewById(R.id.text);
        textView.setText("我是C");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment;
    }
}
