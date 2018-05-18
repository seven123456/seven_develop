package com.seven.seven.home.events;

import com.seven.seven.common.event.BaseEvent;

/**
 * Created by seven
 * on 2018/5/17
 * email:seven2016s@163.com
 */

public class HomeEvents<T> extends BaseEvent {

    public HomeEvents() {
    }

    public HomeEvents(int what) {
        super(what);
    }

    public HomeEvents(int what, T data) {
        super(what, data);
    }

}
