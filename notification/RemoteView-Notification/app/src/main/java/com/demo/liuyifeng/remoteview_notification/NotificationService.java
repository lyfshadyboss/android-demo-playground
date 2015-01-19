package com.demo.liuyifeng.remoteview_notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by liuyifeng on 15-1-17.
 */
public class NotificationService extends Service {
    public static final String TAG = "notification service";
    public static final int NOTIFICATION_ID = 1001;

    private static final String MOBILE_DATA_CHANGED_ACTION = "android.demo.intent.mobile_data_changed";

    private WifiManager mWifiManager;
    private ConnectivityManager mConnectivityManager;

    private Notification mNotification;
    private RemoteViews mContentView;

    private ChangedReceiver mChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "notification service onCreate");

        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mChangeReceiver = new ChangedReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(NotificationService.MOBILE_DATA_CHANGED_ACTION);
        registerReceiver(mChangeReceiver, intentFilter);

        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        MobileDataObserver dataObserver = new MobileDataObserver(this, new Handler());
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("mobile_data"), false, dataObserver);

        startNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "notification onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "notification onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initStatus() {
        checkWifiStatus();
        checkGPRSStatus();
        checkRingerStatus();
        checkGlimStatus();
    }

    private void checkWifiStatus() {
        int state = mWifiManager.getWifiState();
        if (state == WifiManager.WIFI_STATE_ENABLED) {
            Log.d(TAG, "wifi enabled");

            mContentView.setTextColor(R.id.textwifi, getResources().getColor(R.color.tool_on_text));
            mContentView.setImageViewResource(R.id.imagewifi, R.drawable.wifi_on);
        } else if (state == WifiManager.WIFI_STATE_DISABLED) {
            Log.d(TAG, "wifi disabled");

            mContentView.setTextColor(R.id.textwifi, getResources().getColor(R.color.tool_text));
            mContentView.setImageViewResource(R.id.imagewifi, R.drawable.wifi);
        }
    }

    private void checkGPRSStatus() {
        Boolean enabled = Util.getMobileDataEnabled(mConnectivityManager);

        if (enabled) {
            Log.d(TAG, "gprs enabled");

            mContentView.setTextColor(R.id.textgprs, getResources().getColor(R.color.tool_on_text));
            mContentView.setImageViewResource(R.id.imagegprs, R.drawable.gprs_on);
        } else {
            Log.d(TAG, "gprs disabled");

            mContentView.setTextColor(R.id.textgprs, getResources().getColor(R.color.tool_text));
            mContentView.setImageViewResource(R.id.imagegprs, R.drawable.gprs);
        }
    }

    private void checkRingerStatus() {

    }

    private void checkGlimStatus() {

    }

    private void startNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher);
        mContentView = new RemoteViews(getPackageName(), R.layout.notify_page);

        Intent wifiIntent = new Intent();
        wifiIntent.setAction("android.intent.WIFION");
        PendingIntent wifiPendingIntent = PendingIntent.getBroadcast(this, 0, wifiIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent gprsIntent = new Intent();
        gprsIntent.setAction("android.intent.GPRSON");
        PendingIntent gprsPendingIntent = PendingIntent.getBroadcast(this, 0, gprsIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent ringerIntent = new Intent();
        ringerIntent.setAction("android.intent.RINGERON");
        PendingIntent ringerPendingIntent = PendingIntent.getBroadcast(this, 0, ringerIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent glimIntent = new Intent();
        glimIntent.setAction("android.intent.GLIMON");
        PendingIntent glimPendingIntent = PendingIntent.getBroadcast(this, 0, glimIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent setttingIntent = new Intent();
        setttingIntent.setAction("android.intent.APPON");
        setttingIntent.putExtra("appcomponent", "com.android.settings");
        PendingIntent settingPendingIntent = PendingIntent.getBroadcast(this, 0, setttingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent quickIntent = new Intent();
        quickIntent.setAction("android.intent.QUICKON");
        PendingIntent quickPendingIntent = PendingIntent.getBroadcast(this, 0, quickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mContentView.setOnClickPendingIntent(R.id.tool_layout1, wifiPendingIntent);
        mContentView.setOnClickPendingIntent(R.id.tool_layout2, gprsPendingIntent);
        mContentView.setOnClickPendingIntent(R.id.tool_layout3, ringerPendingIntent);
        mContentView.setOnClickPendingIntent(R.id.tool_layout4, glimPendingIntent);
        mContentView.setOnClickPendingIntent(R.id.tool_layout5, settingPendingIntent);
        mContentView.setOnClickPendingIntent(R.id.tool_layout6, quickPendingIntent);

        initStatus();

        mBuilder.setContent(mContentView);
        mNotification = mBuilder.build();
        startForeground(NOTIFICATION_ID, mNotification);
    }

    class ChangedReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                checkWifiStatus();
            } else if (action.equals(MOBILE_DATA_CHANGED_ACTION)) {
                checkGPRSStatus();
            }

            startForeground(NOTIFICATION_ID, mNotification);
        }
    }

    class MobileDataObserver extends ContentObserver {
        Context mContext;

        public MobileDataObserver(Context context, Handler handler) {
            super(handler);
            mContext = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            Intent intent = new Intent();
            intent.setAction(MOBILE_DATA_CHANGED_ACTION);
            mContext.sendBroadcast(intent);
        }

    }
}
