package com.seven.seven.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.seven.seven.R;

/**
 * Created  on 2018-05-24.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomeTitleView extends LinearLayout {
    private Interpolator mInterpolator = new DecelerateInterpolator(3);
    private View view;

    public HomeTitleView(Context context) {
        super(context);
    }

    public HomeTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        view = findViewById(R.id.lly_root);
        setBgAndViewAlpha(0);
    }

    public void setBgAndViewAlpha(int alpha) {
        float animAlpha = mInterpolator.getInterpolation(alpha);
        view.setAlpha(animAlpha);
    }
}
