package com.seven.seven.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.PreferencesUtils;
import com.seven.seven.ui.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by seven
 * on 2018/6/2
 * email:seven2016s@163.com
 */

public class RejisterActivity extends BaseActivity implements RegisterContract.View {

    private TextView login;
    private EditText userEt, password;
    private RegisterPresenter registerPresenter;
    private RegisterInfo registerInfo;

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        registerPresenter = new RegisterPresenter(this, this);
        login = findViewById(R.id.bt_login);
        userEt = findViewById(R.id.et_user);
        password = findViewById(R.id.et_password);
    }

    @Override
    protected void initData() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeRegisterEvent(RegisterEvent registerEvent) {
        switch (registerEvent.getWhat()) {
            case Constans.REGISTER:
                showSuccessToast("注册成功");
                if (registerEvent.getData() != null) {
                    registerInfo = (RegisterInfo) registerEvent.getData();
                }
                PreferencesUtils.putString(RejisterActivity.this, Constans.USERNAME, registerInfo.getUsername());
                startActivity(new Intent(RejisterActivity.this, MainActivity.class));
                finish();
                break;
            case Constans.USERERRORS:
                showErrorToast(registerEvent.getData().toString());
                break;
        }

    }

    @Override
    protected void setLisenter() {
        login.setOnClickListener(this);
        userEt.setOnClickListener(this);
        password.setOnClickListener(this);
    }

    //    String username, String password
    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String username = userEt.getText().toString().trim();
                String passwords = password.getText().toString().trim();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(passwords) && username.length() >= 6 && passwords.length() >= 6) {
                    registerPresenter.register(username, passwords, passwords);
                } else {
                    showErrorToast("账号或密码输入有误");
                }
                break;
            case R.id.et_user:
                break;
            case R.id.et_password:
                break;
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean isNeedTranslateBar() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
