package com.seven.seven.common.utils;

import android.content.Context;
import android.util.Log;

import com.seven.seven.R;
import com.seven.seven.home.model.HomeToWebViewInfo;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by seven
 * on 2018/6/3
 * email:seven2016s@163.com
 */

public class ShareUtils {
    public static void shareApp(Platform platform, Context context) {

        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            Platform.ShareParams shareParams = new Platform.ShareParams();
            //关闭sso授权
//            shareParams.disableSSOWhenAuthorize();
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            shareParams.setTitle("Seven");
            // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
            // text是分享文本，所有平台都需要这个字段
            shareParams.setText("这是一款基于最新技术、博客文章阅读的APP");
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
            shareParams.setUrl("https://fir.im/mche");
            shareParams.setShareType(Platform.SHARE_WEBPAGE);
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("ShareSDK");
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
            //启动分享
            platform.setPlatformActionListener(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    ToastUtils.success("分享成功");
                }

                @Override
                public void onError(Platform platform, int i, Throwable throwable) {
                    Log.d("onError", "i==" + i + "////throwable" + throwable.getMessage());
                    ToastUtils.error("分享失败");
                }

                @Override
                public void onCancel(Platform platform, int i) {
                    ToastUtils.success("取消分享");
                }
            });
            platform.share(shareParams);
        }
    }


    public static void wxShare(Platform platform, HomeToWebViewInfo homeToWebViewInfo) {
        if (platform != null && homeToWebViewInfo != null) {
            //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
            if (platform != null) {
                Platform.ShareParams shareParams = new Platform.ShareParams();
                //关闭sso授权
//            shareParams.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                shareParams.setTitle(homeToWebViewInfo.title);
                // text是分享文本，所有平台都需要这个字段
                shareParams.setText("这是一款基于最新技术、博客文章阅读的APP");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                if (homeToWebViewInfo.imgUrl != null && !homeToWebViewInfo.imgUrl.equals("")) {
                    shareParams.setImageUrl(homeToWebViewInfo.imgUrl);
                }
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                shareParams.setUrl(homeToWebViewInfo.h5Url);
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                //启动分享
                platform.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        ToastUtils.success("分享成功");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Log.d("onError", "i==" + i + "////throwable" + throwable.getMessage());
                        ToastUtils.error("分享失败");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ToastUtils.success("取消分享");
                    }
                });
                platform.share(shareParams);
            }
        }
    }
}
