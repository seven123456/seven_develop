package com.seven.seven.ui.base.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.seven.seven.R;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class BFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ImageView textView = rootView.findViewById(R.id.text);
//        textView.setText("我是B");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_b_fragment;
    }
}
