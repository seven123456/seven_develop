package com.seven.seven.common.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.seven.seven.common.event.NetWorkChangeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Carson_Ho on 16/10/31.
 */
public class HttpErrorReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取ConnectivityManager对象对应的NetworkInfo对象
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                EventBus.getDefault().post(new NetWorkChangeEvent(NetWorkChangeEvent.NET_WORK_AVAILABLE));
            } else {
                EventBus.getDefault().post(new NetWorkChangeEvent(NetWorkChangeEvent.NET_WORK_DISABLED));
            }
        }
    }
}
