package com.seven.seven.search;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.AppManager;

/**
 * Created by seven
 * on 2018/6/6
 * email:seven2016s@163.com
 */

public class LookImageActivity extends BaseActivity {

    private PhotoView photoView;
    private ProgressBar loading;
    private ImageView ivFinsh;

    @Override
    protected void initView(Bundle savedInstanceState) {
        photoView = findViewById(R.id.phtotview);
        loading = findViewById(R.id.pb_pic_browse);
        ivFinsh = findViewById(R.id.iv_finsh);
        photoView.enable();
    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra("image");
        Glide.with(this).load(url).
                into(new DrawableImageViewTarget(photoView) {
                    @Override
                    public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        loading.setVisibility(View.GONE);
                    }
                });

    }

    @Override
    protected void setLisenter() {
        ivFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(mActivity);
            }
        });
    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_look_image;
    }
}
