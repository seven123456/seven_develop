package com.seven.seven.home.presenter;

import com.seven.seven.home.contract.HomeContract;
import com.seven.seven.home.modle.HomeModle;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */

public class HomePresenter extends HomeContract.HomePresenter {
    public HomePresenter(HomeContract.IHomeView iHomeView) {
        mView = iHomeView;
        mModle = new HomeModle();
    }

    @Override
    public void loadBanner() {

    }

    @Override
    public void onStart() {

    }
}
