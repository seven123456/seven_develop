package com.seven.seven.common.event;

/**
 * Created  on 2018-03-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class BaseEvent<T> {
    private int what;
    private T data;

    public BaseEvent(){}

    public BaseEvent(int what) {
        this(what, null);
    }

    public BaseEvent(int what, T data) {
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

    public void setData(T data) {
        this.data = data;
    }
}
