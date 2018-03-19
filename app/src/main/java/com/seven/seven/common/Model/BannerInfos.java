package com.seven.seven.common.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created  on 2018-03-19.
 * author:seven
 * email:seven2016s@163.com
 */

public class BannerInfos implements Serializable {
    public int errorCode;
    public String errorMsg;
    public List<BannerInfo> data;
}
