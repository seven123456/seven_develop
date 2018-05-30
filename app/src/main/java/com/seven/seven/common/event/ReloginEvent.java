package com.seven.seven.common.event;

/**
 * Created  on 2018-05-30.
 * author:seven
 * email:seven2016s@163.com
 */

public class ReloginEvent extends BaseEvent {
    public ReloginEvent() {
    }

    public ReloginEvent(int what) {
        super(what);
    }

    public ReloginEvent(int what, Object data) {
        super(what, data);
    }
}
