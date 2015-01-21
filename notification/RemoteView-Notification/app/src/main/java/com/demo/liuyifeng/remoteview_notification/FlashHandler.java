package com.demo.liuyifeng.remoteview_notification;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;

/**
 * Created by liuyifeng on 15-1-20.
 */
public class FlashHandler {
    private Context mContext;
    private Camera mCamera;

    public static final String ACTION_FLASH_CHANGED = "flash_light_change";
    public static final String STATUS_KEY = "flash_status";
    public static final int STATUS_ON = 0;
    public static final int STATUS_OFF = 1;

    private int mStatus = STATUS_OFF;

    public FlashHandler(Context context) {
        mContext = context;

    }

    public int getStatus() {
        return mStatus;
    }

    public void switchState() {
        int oldStatus = mStatus;

        if (mStatus == STATUS_OFF) {
            openFlash();
        } else {
            closeFlash();
        }

        if (oldStatus != mStatus) {
            Intent intent = new Intent(ACTION_FLASH_CHANGED);
            intent.putExtra(STATUS_KEY, mStatus);
            mContext.sendBroadcast(intent);
        }
    }


    private void openFlash() {
        if (mCamera == null && mStatus != STATUS_ON) {
            try {
                mCamera = Camera.open();
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.setFlashMode("torch");
                mCamera.setParameters(parameters);

                mStatus = STATUS_ON;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void closeFlash() {
        if (mCamera != null && mStatus != STATUS_OFF) {

            try {
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.setFlashMode("off");
                mCamera.setParameters(parameters);

                mStatus = STATUS_OFF;
            } catch (Exception e) {
                e.printStackTrace();
            }

            mCamera.release();
            mCamera = null;
        }
    }

}
