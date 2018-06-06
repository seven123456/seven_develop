package com.seven.seven.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.base.codereview.BaseActivity;
import com.seven.seven.common.utils.AppManager;
import com.seven.seven.common.utils.Constans;
import com.seven.seven.common.utils.PreferencesUtils;
import com.seven.seven.home.events.HomeEvents;
import com.seven.seven.ui.MainActivity;
import com.seven.seven.ui.SplashActivity;
import com.seven.seven.user.view.CanleLoginDialog;

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
    private TextView loginBt, registerBt;
    private LoginPresenter loginPresenter;
    private RegisterInfo registerInfo;
    private AlertDialog alertDilaog;

    @Override
    protected void initView(Bundle savedInstanceState) {
        loginPresenter = new LoginPresenter(this, this);
        EventBus.getDefault().register(this);
        accountEt = findViewById(R.id.et_account);
        passwordEt = findViewById(R.id.et_password);
        loginBt = findViewById(R.id.bt_login);
        registerBt = findViewById(R.id.bt_register);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void disposeLoginEvent(LoginEvent loginEvent) {
        switch (loginEvent.getWhat()) {
            case Constans.LOGIN:
                showSuccessToast("登录成功");
                if (loginEvent.getData() != null) {
                    registerInfo = (RegisterInfo) loginEvent.getData();
                }
                PreferencesUtils.putString(LoginActivity.this, Constans.USERNAME, registerInfo.getUsername());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case Constans.USERERROR:
                showErrorToast((String) loginEvent.getData());
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
                String userNames = accountEt.getText().toString().trim();
                String passwords = passwordEt.getText().toString().trim();
                if (!userNames.isEmpty() && !passwords.isEmpty()) {
                    loginPresenter.login(userNames, passwords);
                } else {
                    showErrorToast("账号或密码不能为空");
                }
                break;
            case R.id.bt_register:
                startActivity(new Intent(mActivity, RejisterActivity.class));
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

    DialogInterface.OnClickListener listenter = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON1:
                    alertDilaog.dismiss();
                    break;
                case AlertDialog.BUTTON2:
//                    AppManager.getAppManager().removedAlllActivity(mActivity);
                    System.exit(0);
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            alertDilaog = new AlertDialog.Builder(mActivity).create();
            alertDilaog.setTitle("你确定要退出App吗？");
            alertDilaog.setMessage("");
            alertDilaog.setButton("取消", listenter);
            alertDilaog.setButton2("确定", listenter);
            alertDilaog.show();
        }
        return false;
    }
}
