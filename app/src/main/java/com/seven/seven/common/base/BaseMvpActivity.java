package com.seven.seven.common.base;

import android.os.Bundle;

import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.base.codereview.BasePresenter;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.ToastUtils;

/**
 * Created  on 2018-01-05.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseMvpActivity<P extends BasePresenter, M extends IBaseModel>
        extends BaseActivity implements IBaseView {
    /*
    * 这里还是属于基类，具体实例化由子类去实现
    * */
    protected P mPresenter;
    protected M mModel;

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }

    /*
            * 通过继承关系，p层去持有v层对象
            * 然后再通过p层去持有M层对象
            * 最后通过P持有 m层和v层对象，这里面存在一个层级关系
            * */
    @Override
    protected void initData() {
   /*     mPresenter = (P) initPresenter();
        if (mPresenter != null) {
//            mModel = (M) mPresenter.getModel();
            if (mModel != null) {
//                mPresenter.attachMV(mModel, this);
            }
        }*/
    }

    /*
    * 通过p层里面的方法来进行解绑v 和M 层的持有关系
    * */
  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }
*/
  /*  @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void back() {
        AppManager.getAppManager().finishActivity(this);
    }*/
}
