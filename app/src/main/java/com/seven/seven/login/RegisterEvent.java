package com.seven.seven.login;

import com.seven.seven.common.event.BaseEvent;

/**
 * Created  on 2018-05-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class RegisterEvent extends BaseEvent {

    public RegisterEvent(int what) {
        super(what);
    }

    public RegisterEvent(int what, Object data) {
        super(what, data);
    }

    public RegisterEvent() {
    }
}
