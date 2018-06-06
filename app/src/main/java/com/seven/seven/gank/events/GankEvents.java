package com.seven.seven.gank.events;

import com.seven.seven.common.event.BaseEvent;

/**
 * Created by seven
 * on 2018/6/5
 * email:seven2016s@163.com
 */

public class GankEvents extends BaseEvent {
    public GankEvents() {
    }

    public GankEvents(int what) {
        super(what);
    }

    public GankEvents(int what, Object data) {
        super(what, data);
    }
}
