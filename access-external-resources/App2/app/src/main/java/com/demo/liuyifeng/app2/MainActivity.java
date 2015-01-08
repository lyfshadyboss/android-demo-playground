package com.demo.liuyifeng.app2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String externalAppName = "";
        View externalLayout = null;

        try {
            Context externalCxt = this.createPackageContext("com.demo.liuyifeng.app1",
                    Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
            externalCxt.setTheme(R.style.AppTheme);

            Resources res = externalCxt.getResources();
            int appname_id = res.getIdentifier("app_name", "string", externalCxt.getPackageName());
            externalAppName = res.getString(appname_id);

            int content_id = res.getIdentifier("content", "layout", externalCxt.getPackageName());
            LayoutInflater inflater = (LayoutInflater) externalCxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            externalLayout = inflater.inflate(content_id, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView text = (TextView) findViewById(R.id.test_text);
        text.setText(externalAppName);

        if (externalLayout != null) {
            LinearLayout contentView = (LinearLayout) findViewById(R.id.content_view);
            contentView.addView(externalLayout);
        }
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
}
