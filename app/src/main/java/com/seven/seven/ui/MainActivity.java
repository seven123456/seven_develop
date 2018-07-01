package com.seven.seven.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.home.HomeFragment;
import com.seven.seven.search.SearchFragment;
import com.seven.seven.gank.GankFragment;
import com.seven.seven.user.UserInfoFragment;
import com.seven.seven.ui.base.fragment.TabItemInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private MainActivity mActivity;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter pagerAdapter;
    private List<TabItemInfo> tabItemInfos;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLisenter() {

    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mActivity = this;
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        initViewPager();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void initViewPager() {
        tabItemInfos = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        tabItemInfos.add(new TabItemInfo(homeFragment, R.drawable.home_button_selector, R.string.tab_main_name));
        SearchFragment searchFragment = new SearchFragment();
        tabItemInfos.add(new TabItemInfo(searchFragment, R.drawable.search_button_selector, R.string.tab_search_name));
        GankFragment gankFragment = new GankFragment();
        tabItemInfos.add(new TabItemInfo(gankFragment, R.drawable.fuli_button_selector, R.string.tab_news_name));
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        tabItemInfos.add(new TabItemInfo(userInfoFragment, R.drawable.my_button_selector, R.string.tab_mine_name));
//        tabItemInfos.add(new EFragment());
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabItemInfos, mActivity);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabTextColors(R.color.black, R.color.red);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        initTabView();
    }

    private void initTabView() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }

    }

    @Override
    protected boolean isNeedTranslateBar() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pagerAdapter != null) {
            pagerAdapter.destroy();
            pagerAdapter = null;
        }
        tabItemInfos = null;
        tabLayout = null;
    }

}
