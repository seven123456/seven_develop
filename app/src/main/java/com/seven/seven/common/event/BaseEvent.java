package com.seven.seven.common.event;

/**
 * Created  on 2018-03-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class BaseEvent {
    private int what;
    private Object data;

    public BaseEvent(){}

    public BaseEvent(int what) {
        this(what, null);
    }

    public BaseEvent(int what, Object data) {
        this.what = what;
        this.data = data;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
