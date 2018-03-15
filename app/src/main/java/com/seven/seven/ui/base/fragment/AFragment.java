package com.seven.seven.ui.base.fragment;

import android.content.Intent;
import android.util.Log;
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

        /*Integer a = 5;
        Integer b = new Integer(5);
        String a1 = "a";
        String b1 = new String("a");
        a.equals(a1);
        Log.d("AFragment--->", (a == b) + "");
        Log.d("AFragment--->", (a.equals(b)) + "");*/
//     TextView textView = rootView.findViewById(R.id.text);
//     textView.setText("我是A");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment;
    }
}
