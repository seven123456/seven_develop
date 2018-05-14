package com.seven.seven.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/13.
 */

public class RollNumberTextView extends LinearLayout {

    private List<RollNumberView> itemViews = new ArrayList<>();
    private int deafultDuration = 300;
    private int startDuration = deafultDuration;

    public RollNumberTextView(Context context) {
        this(context, null);
    }

    public RollNumberTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RollNumberTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }

    public void startAnimator(int startNumber, int endNumber) {
        //reset
        itemViews.clear();
        startDuration = deafultDuration;
        removeAllViews();

        String startStr = String.valueOf(startNumber);
        String endStr = String.valueOf(endNumber);
        RollNumberView rollNumberView;
        if (startStr.length() != endStr.length()) {
            //如果位数不同，特殊处理多出来的位数 0 -> x
            int offsetIndex = endStr.length() - startStr.length();
            for (int i = 0; i < offsetIndex; i++) {
                rollNumberView = new RollNumberView(getContext());
                rollNumberView.setNumber(0, endStr.charAt(i) - '0'); //- '0'  是把char类型的数字转化成int类型的数字  不转结果是Ascii
                itemViews.add(rollNumberView);
                addView(rollNumberView);
            }

            for (int i = 0; i < startStr.length(); i++) {
                rollNumberView = new RollNumberView(getContext());
                rollNumberView.setNumber(startStr.charAt(i) - '0', endStr.charAt(i + offsetIndex) - '0');
                itemViews.add(rollNumberView);
                addView(rollNumberView);
            }

        } else {
            for (int i = 0; i < startStr.length(); i++) {
                rollNumberView = new RollNumberView(getContext());
                rollNumberView.setNumber(startStr.charAt(i) - '0', endStr.charAt(i) - '0');
                itemViews.add(rollNumberView);
                addView(rollNumberView);
            }
        }

        for (RollNumberView item : itemViews) {
            startDuration += 150;
            item.start(startDuration);
        }
    }
}
