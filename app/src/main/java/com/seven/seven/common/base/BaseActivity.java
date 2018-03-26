package com.seven.seven.common.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.seven.seven.R;
import com.seven.seven.common.network.RxLifeManager;
import com.seven.seven.common.utils.AppManager;


/**
 * Created  on 2018-01-04.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean isTransAnim;//是否使用跳转动画
    private HttpErrorReceiver httpErrorReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        AppManager.getAppManager().addActivity(this);
        init(savedInstanceState);
        initData();
        isTransAnim = true;
    }

    protected abstract void initData();

    protected void init(Bundle savedInstanceState) {
        setContentView(getContentViewResId());
        initView(savedInstanceState);
    }

    protected abstract void initView(Bundle savedInstanceState);

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
    * 普通跳转
    * */
    public void startActivity(Class<?> tClass) {
        startActivity(new Intent(this, tClass));
        if (isTransAnim)
            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
                    .activity_start_zoom_out);
    }

    /*
    * 带有bundle数据的跳转
    * */
    public void startActivity(Class<?> tClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, tClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isTransAnim) {
            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
                    .activity_start_zoom_out);
        }
    }

    /*
    * 携带数据跳转并且返回
    * */
    public void startForResultActivity(Class<?> tClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, tClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        if (isTransAnim) {
            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
                    .activity_start_zoom_out);
        }
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

    @Override
    public void finish() {
        super.finish();
        if (isTransAnim)
            overridePendingTransition(R.anim.activity_finish_trans_in, R.anim
                    .activity_finish_trans_out);
    }

    /**
     * 设置是否使用overridePendingTransition过度动画
     */
    protected void setIsTransAnim(boolean b) {
        isTransAnim = b;
    }


}
