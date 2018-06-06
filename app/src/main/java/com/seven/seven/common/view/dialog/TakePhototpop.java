package com.seven.seven.common.view.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.seven.seven.R;

/**
 * Created by seven
 * on 2018/6/5
 * email:seven2016s@163.com
 */

public class TakePhototpop extends PopupWindow {

    private View view;
    private TextView takePhoto;
    private TextView takeAlarm, tvCancle;

    public TakePhototpop(Context context) {
        super(context);
        initView(context);
        setPopView();
    }

    private void setPopView() {
        setContentView(view);
        setOutsideTouchable(true);
        setFocusable(true);
        setTouchable(true);
        setBackgroundDrawable(new ColorDrawable());
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void initView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.pop_take_photo, null);
        takePhoto = view.findViewById(R.id.tv_take_photo);
        takeAlarm = view.findViewById(R.id.tv_take_alarm);
        tvCancle = view.findViewById(R.id.tv_cancel);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takePhotoTouch != null) {
                    takePhotoTouch.takePhoto();

                }
                dismiss();
            }
        });
        takeAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takePhotoTouch != null) {
                    takePhotoTouch.takeAlarm();
                }
                dismiss();
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takePhotoTouch != null) {
                    takePhotoTouch.tvCancle();
                }
                dismiss();
            }
        });
    }
    public void showTakePop(View parent){
        showAtLocation(parent, Gravity.BOTTOM,0,0);
    }
    private TakePhotoTouch takePhotoTouch;

    public void setTakePhotoTouch(TakePhotoTouch touch) {
        this.takePhotoTouch = touch;
    }

    public interface TakePhotoTouch {
        void takePhoto();

        void takeAlarm();

        void tvCancle();
    }
}
