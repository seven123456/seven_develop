package com.seven.seven.home.events;

import com.seven.seven.common.event.BaseEvent;

/**
 * Created  on 2018-05-29.
 * author:seven
 * email:seven2016s@163.com
 */

public class BaseWebViewEvents extends BaseEvent {
    public BaseWebViewEvents() {
    }

    public BaseWebViewEvents(int what) {
        super(what);
    }

    public BaseWebViewEvents(int what, Object data) {
        super(what, data);
    }
}
