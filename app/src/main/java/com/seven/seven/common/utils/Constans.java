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
    public static final int HOMEERROR = 100;//首页请求数据失败
    public static final int HOMEDASUCCESS = 103;//banner,homedata请求数据成功，取消loading

    public static final String COOKIE_PREF = "cookie_pref";//cookie保存
    /*
    * 登录注册用户相关10-20
    * */
    public static final int REGISTER = 10;//注册成功
    public static final int LOGIN = 11;//登录成功
    public static final int USERERROR = 12;//用户相关错误
    public static final String USERNAME = "user_name";//用户相关错误

}
