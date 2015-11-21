package com.example.yifengliu.test;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by yifengliu on 15/11/21.
 */
public class DeviceInfoUtil {
    public static int getPixelFromDip(DisplayMetrics dm, float dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm) + 0.5f);
    }

    public static int getPixelFromDip(float f) {
        return getPixelFromDip(MainApplication.sContext.getResources().getDisplayMetrics(), f);
    }
}
