package com.seven.seven.common.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.utils.DensityUtil;

import java.util.logging.Logger;

/**
 * Created  on 2018-05-14.
 * author:seven
 * email:seven2016s@163.com
 */

public class NumberPlayTextView<T> extends View {
    private int mTextSize;
    private Paint mPaint;
    private T mText;
    private float mViewWidth;
    private float mViewHeight;
    private long runDuration;
    private boolean isInteger;

    public NumberPlayTextView(Context context) {
        super(context);
    }

    public NumberPlayTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public NumberPlayTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberPlayTextView);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.NumberPlayTextView_mTextSize, DensityUtil.dip2px(context, 20));
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        mPaint.setStrokeWidth(1);
    }

    public void setMText(T textValue, long duration) {
        this.mText = textValue;
        this.runDuration = duration;
        mViewWidth = getTextWidth(String.valueOf(textValue), mPaint);
        mViewHeight = getTextHeight(String.valueOf(textValue), mPaint);
        isInteger = textValue instanceof Integer;
        startAnimations();
    }

    private void startAnimations() {
        ValueAnimator valueAnimator;
        if (isInteger) {
            valueAnimator = ValueAnimator.ofInt(0,  (Integer) mText);
        } else {
            valueAnimator = ValueAnimator.ofFloat(0, Float.parseFloat(String.valueOf(mText)));
        }
        valueAnimator.setDuration(runDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mText = isInteger ? (T) animation.getAnimatedValue() : (T) animation.getAnimatedValue();
                ViewCompat.postInvalidateOnAnimation(NumberPlayTextView.this);
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(String.valueOf(mText), getPaddingLeft(), getPaddingRight() + mViewHeight, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public static float getTextWidth(@NonNull String paramText, Paint paramPaint) {
        return paramPaint.measureText(paramText);
    }

    /**
     * @param paramText  显示的文本
     * @param paramPaint 画笔
     * @return 文本的高度
     */
    public static float getTextHeight(String paramText, Paint paramPaint) {
        Rect rect = new Rect();
        paramPaint.getTextBounds(paramText, 0, paramText.length(), rect);
        return rect.height();
    }
}
