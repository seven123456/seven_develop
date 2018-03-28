package com.seven.seven.ui.base.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.base.BaseFragment;
import com.seven.seven.common.utils.ToastUtils;
import com.seven.seven.ui.base.activity.ViewTestActivity;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class EFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void setLisenter() {

    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    protected void initView() {
        TextView textView = rootView.findViewById(R.id.text);
        textView.setText("我是E");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.success("请稍后");
                startActivity(new Intent(getActivity(),ViewTestActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment;
    }
}
