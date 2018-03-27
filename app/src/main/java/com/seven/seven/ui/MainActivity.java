package com.seven.seven.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.DensityUtil;
import com.seven.seven.common.base.BaseActivity;
import com.seven.seven.ui.base.fragment.AFragment;
import com.seven.seven.ui.base.fragment.BFragment;
import com.seven.seven.ui.base.fragment.CFragment;
import com.seven.seven.ui.base.fragment.DFragment;
import com.seven.seven.ui.base.fragment.EFragment;
import com.seven.seven.ui.base.fragment.MyPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private MainActivity mActivity;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] titles = new String[]{"รอการตรวจสอบ", "รอการตรวจสอบ", "รอการตรวจสอบ", "รอการตรวจสอบ", "รอการตรวจสอบ"};
    private String[] title1 = new String[]{"首页", "分类", "新闻", "个人"};
    private int i = 1;
    private MyPagerAdapter pagerAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mActivity = this;
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        initViewPager();
        Log.d("MainActivity", AppManager.getAppManager().getCurrentActivity() + "=========");
        AppManager.getAppManager().getAllActivity();
    }

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new AFragment());
        fragmentList.add(new BFragment());
        fragmentList.add(new CFragment());
        fragmentList.add(new DFragment());
//        fragmentList.add(new EFragment());
        /*if (i == 1) {
            pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        } else {*/
            pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, title1);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        }
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //        setLin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        if (AppManager.getAppManager().isEmptyList()) {
            Log.d("MainActivity", "空了1");
        } else {
            Log.d("MainActivity", "没空了1");
        }
    }

}
