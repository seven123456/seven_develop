package com.seven.seven.ui;

import android.content.Intent;

import com.seven.seven.R;
import com.seven.seven.ui.base.activity.BaseActivity;

/**
 * Created  on 2018-02-02.
 * author:seven
 * email:seven2016s@163.com
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_splash;
    }
}
