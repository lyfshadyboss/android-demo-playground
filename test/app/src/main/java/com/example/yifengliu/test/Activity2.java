package com.example.yifengliu.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yifengliu on 15-7-13.
 */
public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("life cycle", "activity2 created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("life cycle", "activity2 started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("life cycle", "activity2 resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("life cycle", "activity2 paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("life cycle", "activity2 stoped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("life cycle", "activity2 destroyed");
    }
}
