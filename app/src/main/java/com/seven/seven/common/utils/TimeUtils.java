package com.seven.seven.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created  on 2018-05-18.
 * author:seven
 * email:seven2016s@163.com
 */

public class TimeUtils {
    /*
    * time:时间毫秒值
    * inFormat:时间格式
    * */
    public static String longToString(long time, String inFormat) {
        Date date = new Date(time);
        DateFormat format = new SimpleDateFormat(inFormat);
        return format.format(date);
    }
}
