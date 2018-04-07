package com.seven.seven.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.seven.seven.R;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.base.BaseActivity;
import com.seven.seven.home.HomeFragment;
import com.seven.seven.ui.base.fragment.BFragment;
import com.seven.seven.ui.base.fragment.CFragment;
import com.seven.seven.ui.base.fragment.DFragment;
import com.seven.seven.ui.base.fragment.EFragment;
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
    protected void initView(Bundle savedInstanceState) {
        mActivity = this;
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        initViewPager();
    }

    private void initViewPager() {
        tabItemInfos = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        tabItemInfos.add(new TabItemInfo(homeFragment, R.drawable.error, R.string.tab_main_name));
        BFragment bFragment = new BFragment();
        tabItemInfos.add(new TabItemInfo(bFragment, R.drawable.error, R.string.tab_classify_name));
        CFragment cFragment = new CFragment();
        tabItemInfos.add(new TabItemInfo(cFragment, R.drawable.error, R.string.tab_news_name));
        DFragment dFragment = new DFragment();
        tabItemInfos.add(new TabItemInfo(dFragment, R.drawable.error, R.string.tab_mine_name));
//        tabItemInfos.add(new EFragment());
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabItemInfos, mActivity);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(tabItemInfos.size());
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabTextColors(R.color.black, R.color.red);

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
