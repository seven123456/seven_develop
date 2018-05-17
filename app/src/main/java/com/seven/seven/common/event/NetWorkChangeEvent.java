package com.seven.seven.common.event;

/**
 * Created  on 2018-03-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class NetWorkChangeEvent extends BaseEvent {

    public NetWorkChangeEvent() {
    }

    public NetWorkChangeEvent(int what) {
        super(what);
    }

    public NetWorkChangeEvent(int what, Object data) {
        super(what, data);
    }
}
