package com.seven.seven.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.seven.seven.R;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private MainActivity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslate(mActivity,true);
    }
}
