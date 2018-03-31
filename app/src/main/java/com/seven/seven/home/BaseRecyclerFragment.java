package com.seven.seven.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seven.seven.R;
import com.seven.seven.common.base.BaseMvpFragment;
import com.seven.seven.common.base.BasePresenter;
import com.seven.seven.common.base.IBaseModel;

/**
 * Created  on 2018-03-31.
 * author:seven
 * email:seven2016s@163.com
 */

public abstract class BaseRecyclerFragment<P extends BasePresenter, M extends IBaseModel> extends BaseMvpFragment<P, M> {
    private View errorView;
    private View loadingView;
    private View emptyView;

    @Override
    public void onStart() {
        super.onStart();
        ShowLoadingView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        errorView = inflater.inflate(R.layout.error_layout, container, false);
        loadingView = inflater.inflate(R.layout.error_layout, container, false);
        emptyView = inflater.inflate(R.layout.error_layout, container, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowLoadingView();
                onErrorViewOnclick(v);
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void onErrorViewOnclick(View v);

    protected abstract void ShowLoadingView();

}
