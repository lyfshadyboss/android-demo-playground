package com.example.yifengliu.fragmentlifecycle;

import android.util.Log;

/**
 * Created by yifengliu on 16/3/24.
 */
public class LogHelper {
    static final String PREFIX = "CUSTOM_LOG : ";
    static final String DEBUG = PREFIX + "DEBUG";
    static final String ERROR = PREFIX + "ERROR";
    static final String WARNING = PREFIX + "WARNING";
    static final String VERBOSE = PREFIX + "VERBOSE";
    static final String DIVIDER = " : ";

    public static final void d(Object context, String msg) {
        Log.d(DEBUG, context.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static final void e(Object context, String msg) {
        Log.e(ERROR, context.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static final void w(Object context, String msg) {
        Log.w(WARNING, context.getClass().getSimpleName() + DIVIDER + msg);
    }

    public static final void v(Object context, String msg) {
        Log.v(VERBOSE, context.getClass().getSimpleName() + DIVIDER + msg);
    }
}
