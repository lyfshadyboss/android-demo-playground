package com.demo.liuyifeng.remoteview_notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by liuyifeng on 15-1-20.
 */
public class SwitcherCenter extends BroadcastReceiver {
    public static final String SWITCHER_KEY = "switcher_key";

    public static final int SWITCHER_ID_WIFI = 0;
    public static final int SWITCHER_ID_GPRS = 1;
    public static final int SWITCHER_ID_RINGER = 2;
    public static final int SWITCHER_ID_GLIM = 3;
    public static final int SWITCHER_ID_APP = 4;
    public static final int SWITCHER_ID_QUICK = 5;

    public static final String ACTION_WIFI = "com.liuyifeng.demo.intent.WIFI";
    public static final String ACTION_GPRS = "com.liuyifeng.demo.intent.GPRS";
    public static final String ACTION_RINGER = "com.liuyifeng.demo.intent.RINGER";
    public static final String ACTION_GLIM = "com.liuyifeng.demo.intent.GLIM";
    public static final String ACTION_APP = "com.liuyifeng.demo.intent.APP";
    public static final String ACTION_QUICK = "com.liuyifeng.demo.intent.QUICK";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notiIntent = new Intent(NotificationService.INTENT_FILTER_ACTION);
        String action = intent.getAction();

        if (action.equals(ACTION_WIFI)) {
            notiIntent.putExtra(SWITCHER_KEY, SWITCHER_ID_WIFI);
            context.startService(notiIntent);

            return;
        }

        if (action.equals(ACTION_GPRS)) {
            notiIntent.putExtra(SWITCHER_KEY, SWITCHER_ID_GPRS);
            context.startService(notiIntent);

            return;
        }

        if (action.equals(ACTION_RINGER)) {
            notiIntent.putExtra(SWITCHER_KEY, ACTION_RINGER);
            context.startService(notiIntent);

            return;
        }

        if (action.equals(ACTION_GLIM)) {
            notiIntent.putExtra(SWITCHER_KEY, ACTION_GLIM);
            context.startService(notiIntent);

            return;
        }

        if (action.equals(ACTION_APP)) {
            try {
                Intent i = new Intent();
                i.setAction("android.settings.SETTINGS");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return;
        }

        if (action.equals(ACTION_QUICK)) {
        }
    }
}
