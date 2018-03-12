package com.seven.seven.ui.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.seven.seven.common.utils.AppManager;


/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        AppManager.getAppManager().addActivity(this);
        init();
        initData();
    }

    protected abstract void initData();

    private void init() {
        setContentView(getContentViewResId());
        initView();
    }

    protected abstract void initView();

    protected abstract int getContentViewResId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
