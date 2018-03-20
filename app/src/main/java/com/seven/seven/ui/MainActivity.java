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
    private String[] title1 = new String[]{"Waiting for verification", "待dfsghfggfnjfgnjfgnmjfghmfghmf付款", "代fgfgmfghmfghmfghmfdg发货", "待dfsgmgfghmfghmfghmfdg收货", "退dfsgf货"};
    private int i = 1;
    private MyPagerAdapter pagerAdapter;

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
        fragmentList.add(new EFragment());
        if (i == 1) {
            pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        } else {
            pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, title1);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        }
        viewPager.setAdapter(pagerAdapter);
//        viewPager.setPageTransformer(false,new ScaleTransformer());
//        tabLayout.addTab(tabLayout.newTab());
//        reflex(tabLayout);
//        viewPager.setPageMargin(80);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#414141"), Color.parseColor("#FF600A"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF600A"));
        tabLayout.setSelectedTabIndicatorHeight(DensityUtil.dip2px(this, 1));
        //        setLin();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
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

    private void setLin() {

        try {
            //拿到tabLayout的mTabStrip属性
            Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
            mTabStripField.setAccessible(true);

            LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);

            int dp10 = DensityUtil.dip2px(this, 10);

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View tabView = mTabStrip.getChildAt(i);

                //拿到tabView的mTextView属性
                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);

                TextView mTextView = (TextView) mTextViewField.get(tabView);

                tabView.setPadding(0, 0, 0, 0);

                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }

                //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.width = width;
                params.leftMargin = dp10;
                params.rightMargin = dp10;
                tabView.setLayoutParams(params);

                tabView.invalidate();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    /*public static void reflex(final TabLayout tabLayout){
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);
                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);
                    int dp10 = DensityUtil.dip2px(tabLayout.getContext(), 10);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }*/
}
