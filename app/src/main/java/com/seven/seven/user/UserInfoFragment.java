package com.seven.seven.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseFragment;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.GlideUtils;
import com.seven.seven.common.utils.PreferencesUtils;
import com.seven.seven.user.view.UserInfoItemView;

import org.w3c.dom.Text;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class UserInfoFragment extends BaseFragment {

    private ImageView userView, headImgView;
    private UserInfoItemView shareApp, clean, author, collection, setting;
    private TextView userName;

    @Override
    protected void initView() {
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
                break;
            case R.id.ui_author:
                break;
            case R.id.ui_setting:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }
}
