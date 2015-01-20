package com.demo.liuyifeng.remoteview_notification;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

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

    public static void expandStatusBar(Context context) {
        try {
            String methodName = "expand";

            if (Build.VERSION.SDK_INT >= 17) {
                methodName = "expandPanels";
            }

            Object statusbar = context.getSystemService("statusbar");
            if (statusbar != null) {
                statusbar.getClass().getMethod(methodName).invoke(statusbar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void collapseStatusBar(Context context) {
        try {
            String methodName = "collapse";

            if (Build.VERSION.SDK_INT >= 17) {
                methodName = "collapsePanels";
            }

            Object statusbar = context.getSystemService("statusbar");
            if (statusbar != null) {
                statusbar.getClass().getMethod(methodName).invoke(statusbar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
