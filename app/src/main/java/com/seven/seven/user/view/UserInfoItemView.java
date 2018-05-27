package com.seven.seven.user.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seven.seven.R;

import java.util.zip.Inflater;

/**
 * Created by seven
 * on 2018/5/27
 * email:seven2016s@163.com
 */

public class UserInfoItemView extends RelativeLayout {

    private ImageView iconRight, iconLeft;
    private TextView itemText, numText;

    public UserInfoItemView(Context context) {
        super(context);
    }

    public UserInfoItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public UserInfoItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.user_info_item_view, this, true);
        setBackgroundColor(context.getResources().getColor(R.color.white));
        iconLeft = findViewById(R.id.img_left);
        iconRight = findViewById(R.id.img_right);
        itemText = findViewById(R.id.tv_item_text);
        numText = findViewById(R.id.tv_num);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UserInfoItemView);
        if (typedArray != null) {
            Drawable left = typedArray.getDrawable(R.styleable.UserInfoItemView_iconLeft);
            iconLeft.setImageDrawable(left);
            /*Drawable right = typedArray.getDrawable(R.styleable.UserInfoItemView_iconRight);
            iconRight.setImageDrawable(right);*/
            String title = typedArray.getString(R.styleable.UserInfoItemView_itemText);
            itemText.setText(title);
            String num = typedArray.getString(R.styleable.UserInfoItemView_numText);
            numText.setText(num);
        }
        typedArray.recycle();
    }

    public UserInfoItemView setIconLeft(Drawable resId) {
        iconLeft.setImageDrawable(resId);
        return this;
    }

    public UserInfoItemView setIconRight(Drawable resId) {
        iconRight.setImageDrawable(resId);
        return this;
    }

    public UserInfoItemView setItemText(String msg0) {
        itemText.setText(msg0);
        return this;
    }

    public UserInfoItemView setNumText(String msg0) {
        numText.setVisibility(VISIBLE);
        numText.setText(msg0);
        return this;
    }
}
