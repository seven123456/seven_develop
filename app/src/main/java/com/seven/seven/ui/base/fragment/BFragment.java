package com.seven.seven.ui.base.fragment;

import android.view.View;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseFragment;
import com.seven.seven.common.view.MarqueeView;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class BFragment extends BaseFragment {

    private MarqueeView marqueeView;

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
        /*marqueeView = rootView.findViewById(R.id.marquee_view);
        List<String> list = new ArrayList<>();
//        String a = "第1个";
//        "第2个", "第3个", "第4个", "第5个", "第6个", "第7个";
        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "个");
        }
        marqueeView.setText(list);
        marqueeView.setOnMargueeListener(new MarqueeView.OnMargueeListener() {
            @Override
            public void onRollOver() {
                marqueeView.stopScroll();
            }

            @Override
            public void onRollIndex(int position) {
                marqueeView.startScroll();

            }
        });*/
//        ImageView textView = rootView.findViewById(R.id.text);
//        textView.setText("我是B");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_b_fragment;
//        return R.layout.layout_home_fragment;
    }
}
