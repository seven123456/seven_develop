package com.seven.seven.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.ui.base.fragment.TabItemInfo;

import java.util.List;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<TabItemInfo> tabItemInfos;
    private Context mContext;

    public MyPagerAdapter(FragmentManager fm, List<TabItemInfo> tabItemInfos, Context context) {
        super(fm);
        this.tabItemInfos = tabItemInfos;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return tabItemInfos == null ? null : tabItemInfos.get(position).fragment;
    }

    @Override
    public int getCount() {
        return tabItemInfos == null ? 0 : tabItemInfos.size();
    }

    public void destroy() {
        if (tabItemInfos != null) {
            tabItemInfos.clear();
            tabItemInfos = null;
        }
    }

    public View getTabView(int position) {
        TabItemInfo tabItemInfo = tabItemInfos.get(position);
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item_view, null);
        ImageView tabIcon = view.findViewById(R.id.tab_icon);
        tabIcon.setImageResource(tabItemInfo.iconResId);
        TextView tabName = view.findViewById(R.id.tab_name);
        tabName.setText(tabItemInfo.nameResId);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
