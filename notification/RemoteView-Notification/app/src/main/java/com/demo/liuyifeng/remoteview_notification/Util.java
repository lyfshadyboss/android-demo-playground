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

    public static void setMobileDataEnabled(ConnectivityManager connectivityManager, boolean enabled) {
        Class aClass = connectivityManager.getClass();
        Class[] types = new Class[]{Boolean.TYPE};

        try {
            aClass.getMethod("setMobileDataEnabled", types).invoke(connectivityManager, enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
