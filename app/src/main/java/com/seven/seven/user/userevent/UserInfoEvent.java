package com.seven.seven.user.userevent;

import com.seven.seven.common.event.BaseEvent;

/**
 * Created by seven
 * on 2018/5/29
 * email:seven2016s@163.com
 */

public class UserInfoEvent extends BaseEvent {
    public UserInfoEvent() {
    }

    public UserInfoEvent(int what) {
        super(what);
    }

    public UserInfoEvent(int what, Object data) {
        super(what, data);
    }
}
