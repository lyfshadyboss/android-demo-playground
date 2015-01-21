package com.demo.liuyifeng.remoteview_notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
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

    private RingerHandler mRingerHandler;
    private FlashHandler mFlashHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        mRingerHandler = new RingerHandler(this);
        mFlashHandler = new FlashHandler(this);

        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mChangeReceiver = new ChangedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(NotificationService.ACTION_MOBILE_DATA_CHANGED);
        intentFilter.addAction(FlashHandler.ACTION_FLASH_CHANGED);
        intentFilter.addAction(RingerHandler.ACTION_RINGER_MODE_CHANGED);
        registerReceiver(mChangeReceiver, intentFilter);

        mSwitcherCenter = new SwitcherCenter(this);
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
        int switcherId = intent != null ? intent.getIntExtra(SwitcherCenter.SWITCHER_KEY, -1) : -1;
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
                    mRingerHandler.switchState();
                    break;
                case SwitcherCenter.SWITCHER_ID_GLIM:
                    mFlashHandler.switchState();
                    break;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
            mContentView.setTextColor(R.id.textwifi, getResources().getColor(R.color.tool_on_text));
            mContentView.setImageViewResource(R.id.imagewifi, R.drawable.wifi_on);
        } else if (state == WifiManager.WIFI_STATE_DISABLED) {
            mContentView.setTextColor(R.id.textwifi, getResources().getColor(R.color.tool_text));
            mContentView.setImageViewResource(R.id.imagewifi, R.drawable.wifi);
        }
    }

    private void checkGPRSStatus() {
        Boolean enabled = Util.getMobileDataEnabled(mConnectivityManager);

        if (enabled) {
            mContentView.setTextColor(R.id.textgprs, getResources().getColor(R.color.tool_on_text));
            mContentView.setImageViewResource(R.id.imagegprs, R.drawable.gprs_on);
        } else {
            mContentView.setTextColor(R.id.textgprs, getResources().getColor(R.color.tool_text));
            mContentView.setImageViewResource(R.id.imagegprs, R.drawable.gprs);
        }
    }

    private void checkRingerStatus() {
        int status = mRingerHandler.getStatus();

        if (status == AudioManager.RINGER_MODE_NORMAL) {
            mContentView.setImageViewResource(R.id.imageringer, R.drawable.iw_white_ringer_on);
        } else if (status == AudioManager.RINGER_MODE_SILENT) {
            mContentView.setImageViewResource(R.id.imageringer, R.drawable.iw_white_ringer_off);
        } else if (status == AudioManager.RINGER_MODE_VIBRATE) {
            mContentView.setImageViewResource(R.id.imageringer, R.drawable.iw_white_ringer_vibrate);
        }
    }

    private void checkGlimStatus() {
        int status = mFlashHandler.getStatus();

        if (status == FlashHandler.STATUS_ON) {
            mContentView.setTextColor(R.id.textglim, getResources().getColor(R.color.tool_on_text));
            mContentView.setImageViewResource(R.id.imageglim, R.drawable.glim_on);
        } else {
            mContentView.setTextColor(R.id.textglim, getResources().getColor(R.color.tool_text));
            mContentView.setImageViewResource(R.id.imageglim, R.drawable.glim);
        }
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

        mNotification.when = System.currentTimeMillis() + Integer.MAX_VALUE;
        if (Build.VERSION.SDK_INT >= 16) {
            mNotification.priority = Notification.PRIORITY_MAX;
        }

        startForeground(NOTIFICATION_ID, mNotification);
    }

    class ChangedReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                checkWifiStatus();
            } else if (action.equals(ACTION_MOBILE_DATA_CHANGED)) {
                checkGPRSStatus();
            } else if (action.equals(FlashHandler.ACTION_FLASH_CHANGED)) {
                checkGlimStatus();
            } else if (action.equals(RingerHandler.ACTION_RINGER_MODE_CHANGED)) {
                checkRingerStatus();
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
