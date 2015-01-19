package com.demo.liuyifeng.remoteview_notification;

import android.net.ConnectivityManager;

/**
 * Created by liuyifeng on 15-1-19.
 */
public class Util {

    public static boolean getMobileDataEnabled(ConnectivityManager connectivityManager) {
        Boolean result = false;
        Class aClass = connectivityManager.getClass();

        try {
            result = (Boolean) aClass.getMethod("getMobileDataEnabled", null).invoke(connectivityManager, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return result;
    }

}
