package com.seven.seven.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.seven.seven.R;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.ui.base.BaseActivity;

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
    protected void initView(Bundle savedInstanceState) {
        startActivity(new Intent(this, MainActivity.class));

    }

   /* @Override
    protected void onResume() {
        super.onResume();
        if (AppManager.getAppManager().isEmptyList()) {
            Log.d("SplashActivity", "空了");
        } else {
            AppManager.getAppManager().getAllActivity();
            Log.d("SplashActivity", "没空了" + AppManager.getAppManager().getCurrentActivity());
        }
    }*/

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().getAllActivity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AppManager.getAppManager().removedAlllActivity(this);
        }
        return false;
    }
}
