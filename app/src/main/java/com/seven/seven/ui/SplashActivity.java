package com.seven.seven.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.seven.seven.R;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.base.codereview.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created  on 2018-02-02.
 * author:seven
 * email:seven2016s@163.com
 */

public class SplashActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void initData() {

    }

    @Override
    protected void setLisenter() {

    }

    @Override
    protected void widgetClick(View v) {

    }

    private int time = 3;

    @Override
    protected void initView(Bundle savedInstanceState) {
//        startActivity(new Intent(this, MainActivity.class));
        imageView = findViewById(R.id.text);
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        finish();
        /*final Handler handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        imageView.setImageResource(R.drawable.timgs);
                        io.reactivex.Observable.interval(1, TimeUnit.SECONDS).take(3)//角标从0开始
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        if (time-aLong == 1) {
                                            startActivity(new Intent(SplashActivity.this,MainActivity.class));
                                            finish();
                                        }
                                    }
                                });
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message =new Message();
                    message.what=1;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
*/

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
