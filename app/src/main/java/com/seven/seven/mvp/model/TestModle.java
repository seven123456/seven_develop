package com.seven.seven.mvp.model;

import android.database.Observable;
import android.support.annotation.NonNull;

import com.seven.seven.mvp.contract.TestContract;
import com.seven.seven.ui.base.BaseModel;
import com.seven.seven.ui.base.IBaseModel;

/**
 * Created  on 2018-03-15.
 * author:seven
 * email:seven2016s@163.com
 */

public class TestModle extends BaseModel implements TestContract.ITestModle {
    @NonNull
    public static TestModle newInstance() {
        return new TestModle();
    }
    /*
    * 网络请求
    * */
    @Override
    public TestInfo getTestInfo(String a1, String a2, String a3) {
        return new TestInfo("model的数据");
    }
}
