package com.seven.seven.common.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.seven.seven.R;

/**
 * Created  on 2018-03-06.
 * author:seven
 * email:seven2016s@163.com
 */

public class CustomCOnstantsView extends RelativeLayout{

    private Toolbar toolbar;

    public CustomCOnstantsView(Context context) {
        super(context);
    }

    public CustomCOnstantsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);

    }

    public CustomCOnstantsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_constants,this,true);
        toolbar = findViewById(R.id.toolbar);
        initToolbar();
    }

    private void initToolbar() {
     /*   setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

}
