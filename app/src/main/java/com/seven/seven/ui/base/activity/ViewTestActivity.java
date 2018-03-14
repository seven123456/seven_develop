package com.seven.seven.ui.base.activity;

import android.graphics.Color;

import com.seven.seven.R;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.common.view.CustomCOnstantsView;

/**
 * Created  on 2018-03-13.
 * author:seven
 * email:seven2016s@163.com
 */

public class ViewTestActivity extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslate(this, true);
        CustomCOnstantsView customCOnstantsView = findViewById(R.id.custom_view);
        customCOnstantsView.setImageView(R.drawable.is_shuai);
        customCOnstantsView.setToolbarTitle("帅帅帅帅帅");
        customCOnstantsView.setToolbarTitleColor(Color.BLACK);
        customCOnstantsView.setToolbarCollapsingColor(Color.RED);
        customCOnstantsView.getCustomfinshPagerOnclickListener(new CustomCOnstantsView.CustomfinshPagerOnclickListener() {
            @Override
            public void customfinshPager() {
                AppManager.getAppManager().finishActivity(ViewTestActivity.this);
            }
        });
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_view;
    }
}
