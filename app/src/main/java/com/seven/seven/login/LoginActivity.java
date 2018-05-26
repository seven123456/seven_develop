package com.seven.seven.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.home.events.HomeEvents;
import com.seven.seven.ui.MainActivity;
import com.seven.seven.ui.SplashActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created  on 2018-05-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private EditText accountEt, passwordEt;
    private Button loginBt, registerBt;
    private LoginPresenter loginPresenter;
    private RegisterInfo registerInfo;

    @Override
    protected void initView(Bundle savedInstanceState) {
        loginPresenter = new LoginPresenter(this, this);
        EventBus.getDefault().register(this);
        accountEt = findViewById(R.id.et_account);
        passwordEt = findViewById(R.id.et_account);
        loginBt = findViewById(R.id.bt_login);
        registerBt = findViewById(R.id.bt_register);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeLoginEvent(LoginEvent loginEvent) {
        switch (loginEvent.getWhat()) {
            case Constans.REGISTER:
                if (loginEvent.getData() != null) {
                    registerInfo = (RegisterInfo) loginEvent.getData();
                }
                break;
            case Constans.LOGIN:
                showSuccessToast("登录成功");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case Constans.USERERROR:
                showErrorToast(loginEvent.getData().toString());
                break;
        }
    }

    @Override
    protected void setLisenter() {
        loginBt.setOnClickListener(this);
        registerBt.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                if (registerInfo != null) {
                    loginPresenter.login(registerInfo.getUsername(), registerInfo.getPassword());
                }
                break;
            case R.id.bt_register:
                String userName = accountEt.getText().toString();
                String password = passwordEt.getText().toString();
                if (!userName.isEmpty() && !password.isEmpty()) {
                    loginPresenter.register(userName, password, password);
                } else {
                    showErrorToast("账号或密码不能为空");
                }
                break;
        }
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
