package com.seven.seven.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseFragment;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.PreferencesUtils;
import com.seven.seven.login.LoginActivity;
import com.seven.seven.ui.MainActivity;
import com.seven.seven.user.contract.UserInfoContract;
import com.seven.seven.user.model.CollectListInfos;
import com.seven.seven.user.presenter.UserInfoPresenter;
import com.seven.seven.user.userevent.UserInfoEvent;
import com.seven.seven.user.view.UserInfoItemView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class UserInfoFragment extends BaseFragment implements UserInfoContract.View {

    private ImageView userView, headImgView;
    private UserInfoItemView shareApp, clean, author, collection, setting;
    private TextView userName;
    private UserInfoPresenter userInfoPresenter;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        userInfoPresenter = new UserInfoPresenter(this, (MainActivity) getActivity());
        headImgView = rootView.findViewById(R.id.img_head_view);
        userView = rootView.findViewById(R.id.img_user_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timgs);
        headImgView.setImageBitmap(RenderscriptUtils.blurBitmap(bitmap, getContext()));
        userView.setImageBitmap(bitmap);
        shareApp = rootView.findViewById(R.id.ui_share);
        clean = rootView.findViewById(R.id.ui_clean);
        collection = rootView.findViewById(R.id.ui_collection);
        author = rootView.findViewById(R.id.ui_author);
        setting = rootView.findViewById(R.id.ui_setting);
        userName = rootView.findViewById(R.id.tv_user_name);
    }

    @Override
    protected void initData() {
        if (PreferencesUtils.getString(getContext(), Constans.USERNAME) != null) {
            userName.setText(PreferencesUtils.getString(getContext(), Constans.USERNAME));
        }
    }

    @Override
    protected void setLisenter() {
        shareApp.setOnClickListener(this);
        clean.setOnClickListener(this);
        author.setOnClickListener(this);
        collection.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ui_share:
                break;
            case R.id.ui_clean:
                break;
            case R.id.ui_collection:
                startActivity(new Intent(getContext(), CollectListActivity.class));
                break;
            case R.id.ui_author:
                break;
            case R.id.ui_setting:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userInfoPresenter.getCollectSize(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeCollectSizeEvents(UserInfoEvent userInfoEvent) {
        switch (userInfoEvent.getWhat()) {
            case Constans.COLLECTSIZE:
                CollectListInfos collectListInfosList = (CollectListInfos) userInfoEvent.getData();
                if (collectListInfosList.getDatas().size() != 0) {
                    collection.setNumText(String.valueOf(collectListInfosList.getDatas().size()));
                }

                break;
            case Constans.COLLECTSIZEERROR:
                showErrorToast(userInfoEvent.getData().toString());
            case Constans.RELOGIN:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }
}
