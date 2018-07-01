package com.seven.seven.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.seven.seven.common.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 搜索词自定义组件，根据列表自定义排版、绘制和点击
 * Created by chenjingmian on 18/2/26.
 */

public class KeyLayout /*extends View*/ {
/*
    private Paint mPaint;
    private TextPaint mTextPaint;
    private List<String> mHistoryList;
    private List<KeyBean> mHotList;
    private List<RectF> mItems;

    // V1.11 加入图文
    private Rect mIconSrcRect;
    private RectF mIconDstRect;
    private Bitmap mIcon;
    private int mIconWidth, mIconHeight;

    private int mTotalNum;
    private int mItemHeight, mHeight, mItemPadding, mCorners, mDrawablePadding;
    private float mTextBaseLine;
    private onItemSelect mCallback;
    private boolean mIsHot; // 是否是热门搜索

    public KeyLayout(Context context) {
        super(context);
        init(context);
    }

    public KeyLayout(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyLayout(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setClickable(true);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(DensityUtil.dip2px(context,14));

        mCorners = DensityUtil.dip2px(context,3);
        mItemHeight = DensityUtil.dip2px(context,32);
        mItemPadding = DensityUtil.dip2px(context,14);
        mTextBaseLine = mItemHeight / 2  - (mTextPaint.descent() + mTextPaint.ascent()) / 2;
        mDrawablePadding = DensityUtil.dip2px(context,6);

        mIconSrcRect = new Rect();
        mIconDstRect = new RectF();
        mIcon = BitmapFactory.decodeResource(getResources(), R.drawable.search_ic_key_url);
        mIconSrcRect.set(0, 0, mIcon.getWidth(), mIcon.getHeight());
        mIconWidth = DensityUtil.dip2px(context,12.5f);
        mIconHeight = DensityUtil.dip2px(context,15.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mHeight);
    }

    public void setHistoryList(List<String> list) {
        mHistoryList = list;
        mTotalNum = list.size();
        mIsHot = false;
        initLayout();
    }

    public void setHotList(List<KeyBean> list) {
        mHotList = list;
        mTotalNum = list.size();
        mIsHot = true;
        initLayout();
    }


    public void setOnItemSelect(onItemSelect callback) {
        mCallback = callback;
    }



    private void initLayout() {
        mItems = new ArrayList<>();
        RectF rectF;

        int itemMarginH = DrawUtils.dip2px(10), itemMarginV = DrawUtils.dip2px(12); // 按钮内部padding和间距
        int totalWidth = DrawUtils.sWidthPixels - DrawUtils.dip2px(15) * 2; // 最大的宽度
        float tempWidth = 0, itemWidth; // tempWidth：临时宽度
        float totalHeight = mItemHeight;

        boolean hasUrl;
        String text;
        for (int i = 0; i < mTotalNum; i++) {
            if (mIsHot) {
                text = mHotList.get(i).getKeyword();
                hasUrl = mHotList.get(i).hasUrl();
            } else {
                text = mHistoryList.get(i);
                hasUrl = false;
            }
            rectF = new RectF();

            itemWidth = mTextPaint.measureText(text) + 2 * mItemPadding;
            if (hasUrl) {
                itemWidth += (mIconWidth + mDrawablePadding);
            }

            itemWidth = Math.min(itemWidth, totalWidth);

            if (tempWidth != 0) {
                tempWidth += itemMarginH;
            }
            rectF.left = tempWidth;
            tempWidth += itemWidth;
            // 一行放不下,另起一行
            if (rectF.left > 0 && tempWidth > totalWidth) {
                tempWidth = itemWidth;
                totalHeight += (itemMarginV + mItemHeight);
                rectF.left = 0;
            }

            rectF.right = rectF.left + itemWidth;
            rectF.bottom = totalHeight;
            rectF.top = rectF.bottom - mItemHeight;

            mItems.add(rectF);

        }
        mHeight = (int) totalHeight;

        requestLayout();
        invalidate();
    }

    private int mDownItem;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mHistoryList != null || mHotList != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN :
                    mDownItem = getItem(event);
                    break;

                case MotionEvent.ACTION_UP:
                    if (mDownItem == getItem(event) && mDownItem != -1) { // down和up同一个item
                        if (mCallback != null) {
                            mCallback.onItemSelect(mDownItem, mIsHot);
                        }
                    }
                    break;

            }
        }
        return super.onTouchEvent(event);
    }

    private int getItem(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mHistoryList == null && mHotList == null) {
            return;
        }
        String text;
        boolean hasUrl;
        RectF rectF;
        int totalWidth = DrawUtils.sWidthPixels - DrawUtils.dip2px(15) * 2; // 最大的宽度
        for (int i = 0; i < mTotalNum; i++) {
            if (mIsHot) {
                text = mHotList.get(i).getKeyword();
                hasUrl = mHotList.get(i).hasUrl();
            } else {
                text = mHistoryList.get(i);
                hasUrl = false;
            }

            rectF = mItems.get(i);
            mPaint.setColor(0xfff0f0f0);
            canvas.drawRoundRect(rectF, mCorners, mCorners, mPaint);
            mTextPaint.setColor(0xff69696f);

            if (rectF.width() >= totalWidth) {
                int maxTextWidth;
                if (hasUrl) {
                    maxTextWidth = totalWidth - 2 * mItemPadding - mIconWidth - mDrawablePadding;
                } else {
                    maxTextWidth = totalWidth - 2 * mItemPadding;
                }
                text = TextUtils.ellipsize(text, mTextPaint, maxTextWidth, TextUtils.TruncateAt.MIDDLE).toString();
            }


            if (hasUrl) {
                mIconDstRect.left = rectF.left + mItemPadding;
                mIconDstRect.top = rectF.top + (rectF.height() - mIconHeight) / 2;
                mIconDstRect.right = mIconDstRect.left + mIconWidth;
                mIconDstRect.bottom = mIconDstRect.top + mIconHeight;
                canvas.drawBitmap(mIcon, mIconSrcRect, mIconDstRect, mPaint);
                canvas.drawText(text, mIconDstRect.right + mDrawablePadding,
                        rectF.top + mTextBaseLine, mTextPaint);
            } else {
                if (rectF.width() >= totalWidth) {
                    canvas.drawText(text, mItemPadding,
                            rectF.top + mTextBaseLine, mTextPaint);
                } else {
                    canvas.drawText(text, rectF.centerX() - mTextPaint.measureText(text) / 2,
                            rectF.top + mTextBaseLine, mTextPaint);
                }
            }
        }
    }

    public interface onItemSelect {
        void onItemSelect(int position, boolean isHot);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mCallback = null;
        mIcon = null;
    }*/
}
