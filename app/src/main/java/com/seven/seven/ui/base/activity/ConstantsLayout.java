package com.seven.seven.ui.base.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;

/**
 * Created  on 2018-02-25.
 * author:seven
 * email:seven2016s@163.com
 */

public class ConstantsLayout extends BaseActivity {
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
    protected void initView(Bundle savedInstanceState) {
//        StatusBarUtil.setTranslate(this, true);
        initToolBar();
        ImageView imageView = findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.p8);
      /*  WebView webView =findViewById(R.id.webs);
        //声明WebSettings子类
         WebSettings webSettings = webView.getSettings();
        // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
         webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        // 支持插件 webSettings.setPluginsEnabled(true);
        // 设置自适应屏幕，两者合用
         webSettings.setUseWideViewPort(true);
        // 将图片调整到适合webview的大小
         webSettings.setLoadWithOverviewMode(true);
        // 缩放至屏幕的大小 //缩放操作
         webSettings.setSupportZoom(true);
        // 支持缩放，默认为true。是下面那个的前提。
         webSettings.setBuiltInZoomControls(true);
        // 设置内置的缩放控件。若为false，则该WebView不可缩放 webSettings.setDisplayZoomControls(false);
        // 隐藏原生的缩放控件
        // 其他细节操作
         webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 关闭webview中缓存 webSettings.setAllowFileAccess(true);
        // 设置可以访问文件 webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 支持通过JS打开新窗口
         webSettings.setLoadsImagesAutomatically(true);
        // 支持自动加载图片
         webSettings.setDefaultTextEncodingName("utf-8");
        // 设置编码格式
        webView.loadUrl("https://www.dhbike.cn/");*/
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("这是折叠toolbar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.layout_constants;
    }

}
