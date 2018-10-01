package com.mydelivery.untils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.Serializable;



public class CheckConnection implements Serializable {
    Context context;
    public CheckConnection(Context context) {
        this.context = context;
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager;
        NetworkInfo wifiInfo, mobileInfo;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo.isConnected() || mobileInfo.isConnected())
            return true;
        else
            return false;
    }

}
