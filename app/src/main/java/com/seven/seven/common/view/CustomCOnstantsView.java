package com.seven.seven.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.seven.seven.R;

/**
 * Created  on 2018-03-06.
 * author:seven
 * email:seven2016s@163.com
 */

public class CustomCOnstantsView extends RelativeLayout {

    private Toolbar toolbar;
    private Context mContext;
    private ImageView imageBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CustomfinshPagerOnclickListener pagerOnclickListener;

    public void getCustomfinshPagerOnclickListener(CustomfinshPagerOnclickListener customfinshPagerOnclickListener) {
        pagerOnclickListener = customfinshPagerOnclickListener;
    }

    public interface CustomfinshPagerOnclickListener {
        void customfinshPager();
    }

    public CustomCOnstantsView(Context context) {
        super(context);
    }

    public CustomCOnstantsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context, attrs);

    }

    public CustomCOnstantsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_constants, this, true);
        toolbar = findViewById(R.id.toolbar);
        initToolbar();
        imageBar = findViewById(R.id.image_view);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.constantsView);
        if (typedArray != null) {
            /*toolbar的文字*/
            String toolbarTitle = typedArray.getString(R.styleable.constantsView_toolbarTitle);
            toolbar.setTitle(toolbarTitle);
            /*toolbar折叠后的颜色*/
            int color = typedArray.getColor(R.styleable.constantsView_toolbarCollapsingColor, getResources().getColor(R.color.colorPrimary));
            collapsingToolbarLayout.setContentScrimColor(color);
            /*折叠后title的颜色*/
            int titleColor = typedArray.getColor(R.styleable.constantsView_toolbarTitleCollapsingColor, getResources().getColor(R.color.red));
            collapsingToolbarLayout.setCollapsedTitleTextColor(titleColor);
        }

        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerOnclickListener.customfinshPager();
            }
        });
    }

    private void initToolbar() {
        ((AppCompatActivity) mContext).setSupportActionBar(toolbar);
        ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    * 设置toolbar的文字
    * */
    public void setToolbarTitle(String resId) {
        if (resId != null) {
            collapsingToolbarLayout.setTitle(resId);
        }
    }

    /*
    * 设置toolbar的文字颜色 接收的colorint，而不是resources
    * */
    public void setToolbarTitleColor(int resId) {
        collapsingToolbarLayout.setCollapsedTitleTextColor(resId);
    }

    /*
    * 设置toolbar折叠后的颜色
    *
    * */
    public void setToolbarCollapsingColor(int resId) {
        collapsingToolbarLayout.setContentScrimColor(resId);
    }
    /*
    * 设置要渐变的imageview
    * */

    public void setImageView(int resId) {
        imageBar.setImageResource(resId);
    }
}
