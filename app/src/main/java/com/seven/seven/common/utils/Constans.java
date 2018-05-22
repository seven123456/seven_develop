package com.seven.seven.common.utils;

/**
 * Created by seven
 * on 2018/5/17
 * email:seven2016s@163.com
 * eventbus的what记录以及一些常量的配置
 */

public class Constans {
    /*
    * 网络监听
    * */
    public static final int NET_WORK_AVAILABLE = 0;//有网络
    public static final int NET_WORK_DISABLED = 1;//没网络
    /*
    * home模块 100~200
    * */
    public static final int HOMEDATA = 101;//获取首页数据成功
    public static final int HOMEBANNER = 102;//获取首页banner
    public static final int HOMEDATAFIAL = 100;//首页请求数据失败
    public static final int HOMEDASUCCESS = 103;//banner,homedata请求数据成功，取消loading


}
