package com.seven.seven.common.event;

/**
 * Created  on 2018-03-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class NetWorkChangeEvent extends BaseEvent {
    public static final int NET_WORK_AVAILABLE = 0;//有网络
    public static final int NET_WORK_DISABLED = 1;//没网络

    public NetWorkChangeEvent() {
    }

    public NetWorkChangeEvent(int what) {
        super(what);
    }

    public NetWorkChangeEvent(int what, Object data) {
        super(what, data);
    }
}
