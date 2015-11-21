package com.example.yifengliu.test;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by yifengliu on 15-9-6.
 */
public class MainApplication extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        Log.d("life cycle", "application created");

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
