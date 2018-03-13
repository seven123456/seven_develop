package com.seven.seven.ui.base.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.seven.seven.R;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class AFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
     TextView textView = rootView.findViewById(R.id.text);
     textView.setText("我是A");
     
    /* textView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(getContext(), TestActivity.class));
         }
     });*/
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment;
    }
}
