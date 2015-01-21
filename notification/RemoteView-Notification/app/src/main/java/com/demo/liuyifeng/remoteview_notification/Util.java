package com.demo.liuyifeng.remoteview_notification;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by liuyifeng on 15-1-19.
 */
public class Util {

    private long cached;

    public static boolean getMobileDataEnabled(ConnectivityManager connectivityManager) {
        Boolean result = false;
        Class aClass = connectivityManager.getClass();

        try {
            result = (Boolean) aClass.getMethod("getMobileDataEnabled").invoke(connectivityManager);
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

    @SuppressWarnings("ResourceType")
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

    @SuppressWarnings("ResourceType")
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

    public static long getFreeMemoryByM() {
        long free = getFreeMemory();

        if (free != -1) {
            return (long) (free / 1024f);
        }

        return -1;
    }

    public static long getTotalMemoryByM() {
        long free = getTotalMemory();

        if (free != -1) {
            return (long) (free / 1024f);
        }

        return -1;
    }

    private static long getTotalMemory() {
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"));

            String line = bufferedReader.readLine();
            if (line != null && line.startsWith("MemTotal:")) {
                line = line.split(" +")[1];
            }

            if (bufferedReader != null) {
                bufferedReader.close();
            }

            return Long.valueOf(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    private static long getFreeMemory() {
        BufferedReader bufferedReader;
        long free = 0, buffer = 0, cached = 0;

        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"));

            while (true) {
                String line = bufferedReader.readLine();

                if (line != null && line.startsWith("MemFree:")) {
                    free = Long.valueOf(line.split(" +")[1]);
                    continue;
                }

                if (line != null && line.startsWith("Buffers:")) {
                    buffer = Long.valueOf(line.split(" +")[1]);
                    continue;
                }

                if (line != null && line.startsWith("Cached:")) {
                    cached = Long.valueOf(line.split(" +")[1]);
                    break;
                }
            }

            if (bufferedReader != null) {
                bufferedReader.close();
            }

            return free + buffer + cached;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
