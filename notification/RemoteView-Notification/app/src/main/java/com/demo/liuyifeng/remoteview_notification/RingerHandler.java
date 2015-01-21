package com.demo.liuyifeng.remoteview_notification;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

/**
 * Created by liuyifeng on 15-1-20.
 */
public class RingerHandler {
    private Context mContext;
    private AudioManager mAudioManager;

    public static final String ACTION_RINGER_MODE_CHANGED = "ringer_mode_changed";

    private int mStatus = AudioManager.RINGER_MODE_NORMAL;

    public RingerHandler(Context context) {
        mContext = context;
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    public int getStatus() {
        return mStatus;
    }

    public void switchState() {
        mStatus = (mStatus + 1) % (AudioManager.RINGER_MODE_NORMAL + 1);
        mAudioManager.setRingerMode(mStatus);

        Intent intent = new Intent(ACTION_RINGER_MODE_CHANGED);
        mContext.sendBroadcast(intent);
    }
}