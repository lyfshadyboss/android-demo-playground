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

    public static final String INTENT_FILTER_ACTION = "com.liuyifeng.demo.intent.NOTIFICATION";
    public static final String ACTION_MOBILE_DATA_CHANGED = "com.liuyifeng.demo.intent.MOBILE_DATA_CHANGED";

    private WifiManager mWifiManager;
    private ConnectivityManager mConnectivityManager;

    private Notification mNotification;
    private RemoteViews mContentView;

    private ChangedReceiver mChangeReceiver;
    private SwitcherCenter mSwitcherCenter;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "notification service onCreate");

        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mChangeReceiver = new ChangedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(NotificationService.ACTION_MOBILE_DATA_CHANGED);
        registerReceiver(mChangeReceiver, intentFilter);

        mSwitcherCenter = new SwitcherCenter();
        intentFilter = new IntentFilter();
        intentFilter.addAction(SwitcherCenter.ACTION_WIFI);
        intentFilter.addAction(SwitcherCenter.ACTION_GPRS);
        intentFilter.addAction(SwitcherCenter.ACTION_RINGER);
        intentFilter.addAction(SwitcherCenter.ACTION_GLIM);
        intentFilter.addAction(SwitcherCenter.ACTION_APP);
        intentFilter.addAction(SwitcherCenter.ACTION_QUICK);
        registerReceiver(mSwitcherCenter, intentFilter);

        MobileDataObserver dataObserver = new MobileDataObserver(this, new Handler());
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("mobile_data"), false, dataObserver);

        startNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "notification onStartCommand");

        int switcherId = intent.getIntExtra(SwitcherCenter.SWITCHER_KEY, -1);
        if (switcherId >= 0) {
            switch (switcherId) {
                case SwitcherCenter.SWITCHER_ID_WIFI:
                    mWifiManager.setWifiEnabled(!mWifiManager.isWifiEnabled());
                    break;
                case SwitcherCenter.SWITCHER_ID_GPRS:
                    boolean enabled = Util.getMobileDataEnabled(mConnectivityManager);
                    Util.setMobileDataEnabled(mConnectivityManager, !enabled);
                    break;
                case SwitcherCenter.SWITCHER_ID_RINGER:
                    break;
                case SwitcherCenter.SWITCHER_ID_GLIM:
                    break;
            }
        }

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

        Intent wifiIntent = new Intent(SwitcherCenter.ACTION_WIFI);
        PendingIntent wifiPendingIntent = PendingIntent.getBroadcast(this, 0, wifiIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent gprsIntent = new Intent(SwitcherCenter.ACTION_GPRS);
        PendingIntent gprsPendingIntent = PendingIntent.getBroadcast(this, 0, gprsIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent ringerIntent = new Intent(SwitcherCenter.ACTION_RINGER);
        PendingIntent ringerPendingIntent = PendingIntent.getBroadcast(this, 0, ringerIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent glimIntent = new Intent(SwitcherCenter.ACTION_GLIM);
        PendingIntent glimPendingIntent = PendingIntent.getBroadcast(this, 0, glimIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent setttingIntent = new Intent(SwitcherCenter.ACTION_APP);
        setttingIntent.putExtra("appcomponent", "com.android.settings");
        PendingIntent settingPendingIntent = PendingIntent.getBroadcast(this, 0, setttingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent quickIntent = new Intent(SwitcherCenter.ACTION_QUICK);
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
            } else if (action.equals(ACTION_MOBILE_DATA_CHANGED)) {
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
            intent.setAction(ACTION_MOBILE_DATA_CHANGED);
            mContext.sendBroadcast(intent);
        }
    }
}
