package com.minglei.jread.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.minglei.jread.JApplication;

/**
 * Created by minglei on 2017/11/14.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static ConnectivityManager sConnectivityManager = null;
    private static Context sCtx = null;

    static {
        sCtx = JApplication.getAppContext();
    }

    public static boolean isNetworkAvailable() {
        return getAvailableNetwork() != null;
    }

    /**
     * @return the current available network info. {@code null} if no available
     * network.
     */
    public static NetworkInfo getAvailableNetwork() {
        try {
            NetworkInfo info = getConnectivityManager().getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                return info;
            }
        } catch (RuntimeException e) {
                return null;
        }
        return null;
    }
    private static ConnectivityManager getConnectivityManager() {
        if (sConnectivityManager == null) {
            sConnectivityManager = (ConnectivityManager) sCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return sConnectivityManager;
    }
}
