package com.seven.seven.search.events;

import com.seven.seven.common.event.BaseEvent;

/**
 * Created by seven
 * on 2018/6/1
 * email:seven2016s@163.com
 */

public class SearchKEvent extends BaseEvent {
    public SearchKEvent() {
    }

    public SearchKEvent(int what) {
        super(what);
    }

    public SearchKEvent(int what, Object data) {
        super(what, data);
    }
}
