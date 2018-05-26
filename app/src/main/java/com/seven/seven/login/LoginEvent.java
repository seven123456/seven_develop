package com.seven.seven.login;

import com.seven.seven.common.event.BaseEvent;

/**
 * Created  on 2018-05-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class LoginEvent extends BaseEvent {

    public LoginEvent(int what) {
        super(what);
    }

    public LoginEvent(int what, Object data) {
        super(what, data);
    }

    public LoginEvent() {
    }
}
