package com.demo.liuyifeng.remoteview_notification;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;

/**
 * Created by liuyifeng on 15-1-20.
 */
public class MemoryCleaner {
    private Context mContext;

    public MemoryCleaner(Context context) {
        mContext = context;
    }

    public void clearMemory() {
        long free = Util.getFreeMemoryByM();
        clear();
        long newFree = Util.getFreeMemoryByM();

        showNotification(newFree - free);
    }

    private void clear() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        Iterator iterator = am.getRunningAppProcesses().iterator();

        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo processInfo =
                    (ActivityManager.RunningAppProcessInfo) iterator.next();

            if (processInfo.processName.startsWith("android") ||
                    processInfo.processName.equals("system") ||
                    processInfo.processName.equals("com.android.alarmclock") ||
                    processInfo.processName.equals("com.android.phone") ||
                    processInfo.processName.equals("com.google.process.gapps") ||
                    processInfo.processName.equals("android.process.acore")) {
                continue;
            }

            am.killBackgroundProcesses(processInfo.pkgList[0]);
        }
    }

    private void showNotification(long cleared) {
        
    }
}
