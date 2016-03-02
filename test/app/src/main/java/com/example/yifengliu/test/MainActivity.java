package com.example.yifengliu.test;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    Test6 test6;
    Test7 test7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test11);

        Fragment f = new TestFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(f, "test");
        ft.commit();

        try {
            test6 = (Test6) findViewById(R.id.test6_group);
            test7 = (Test7) findViewById(R.id.test7_group);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("life cycle", "activity1 created");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("life cycle", "activity1 started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (test6 != null) test6.onResume();
        if (test7 != null) test7.onResume();

        Log.d("life cycle", "activity1 resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (test6 != null) test6.onPause();
        if (test7 != null) test7.onPause();

        Log.d("life cycle", "activity1 paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (test6 != null) test6.onStop();
        if (test7 != null) test7.onStop();

        Log.d("life cycle", "activity1 stoped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("life cycle", "activity1 destroyed");
    }
}
