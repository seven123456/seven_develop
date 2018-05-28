package com.seven.seven.common.base.codereview;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.seven.seven.R;
import com.seven.seven.common.network.RxLifeManager;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.StatusBarUtil;
import com.seven.seven.common.utils.ToastUtils;


/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        AppManager.getAppManager().addActivity(this);
        mActivity = this;
        if (isNeedTranslateBar()) {
            StatusBarUtil.setTranslate(mActivity, true);
        }
        init(savedInstanceState);
        initData();
        setLisenter();
    }

    protected boolean isNeedTranslateBar() {
        return true;
    }

    protected void init(Bundle savedInstanceState) {
        setContentView(getContentViewResId());
        initView(savedInstanceState);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void setLisenter();

    protected abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    protected abstract int getContentViewResId();

    @Override
    protected void onStop() {
        super.onStop();
        RxLifeManager.getRxLifeManager().onStopDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        RxLifeManager.getRxLifeManager().onDestroy();
    }

    /*
    * 统一处理toolbar的基本设置
    * */
    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /*
    * 带图片的toast
    * */
    public void showSuccessToast(String msg) {
        ToastUtils.success(msg);
    }

    /*
    * error的toast
    * */
    public void showErrorToast(String msg) {
        ToastUtils.error(msg);
    }


}
