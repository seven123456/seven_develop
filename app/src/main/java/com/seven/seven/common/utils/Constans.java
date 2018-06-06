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
    public static final int RELOGIN = 13;//重新登录
    public static final int USERERROR = 12;//用户相关错误
    public static final int USERERRORS = 14;//注册相关错误
    public static final String USERNAME = "user_name";//存储用户名
    public static final int COLLECTSIZE = 41;//获取收藏size
    public static final int COLLECTSIZEERROR = 42;//获取收藏size失败
    /*
    * webview模块 20-40
    * */
    public static final int COLLECT = 20;//收藏文章成功
    public static final int COLLECTERROR = 22;//收藏文章失败

    /*
    * 个人模块 40-60
    * */
    public static final int COLLECTLIST = 41;//获取收藏列表成功
    public static final int COLLECTLISTERROR = 42;//获取收藏列表失败
    public static final int DELETECOLLECT = 43;//获取收藏列表失败
    public static final int DELETECOLLECTERROR = 44;//获取收藏列表失败

    /*
    * 搜索模块 60~80
    * */
    public static final int HOTTAG = 60;//获取热门搜索词成功
    public static final int HOTTAGERROR = 61;//获取热门搜索词失败
    public static final int USINGURL = 62;//获取常用网址成功
    public static final int USINGURLERROR = 63;//获取常用网址失败
    public static final int SEARCHK = 64;//动态搜索成功
    public static final int SEARCHKERROR = 65;//动态搜索失败

    /*
    * 干货模块 80~99
    *
    * */
    public static final int GANKIO = 80;//获取干货福利成功
    public static final int GANKIOERROR = 81;//获取干货福利失败
}
