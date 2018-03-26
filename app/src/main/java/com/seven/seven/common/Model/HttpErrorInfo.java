package com.seven.seven.common.Model;

import java.io.Serializable;

/**
 * Created  on 2018-03-26.
 * author:seven
 * email:seven2016s@163.com
 */

public class HttpErrorInfo implements Serializable {
    private String msg;

    public HttpErrorInfo(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
