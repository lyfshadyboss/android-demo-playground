package com.example.yifengliu.listviewanimation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView listview;
    private int mode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listview = new ListView(this);
        listview.setFadingEdgeLength(0);
        ArrayList<String> strings = new ArrayList<String>();

        for (int i = 0; i < 100; i++) {
            strings.add("Item:#" + (i + 1));
        }

        MainAdapter mAdapter = new MainAdapter(this, strings);
        listview.setAdapter(mAdapter);
        listview.setBackgroundResource(android.R.color.holo_blue_light);
        listview.setDivider(null);

        setContentView(listview);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, 1, 0, "fade_in");
        menu.add(Menu.NONE, 2, 0, "hyper_space_in");
        menu.add(Menu.NONE, 3, 0, "hyper_space_out");
        menu.add(Menu.NONE, 4, 0, "wave_scale");
        menu.add(Menu.NONE, 5, 0, "push_left_in");
        menu.add(Menu.NONE, 6, 0, "push_left_out");
        menu.add(Menu.NONE, 7, 0, "push_up_in");
        menu.add(Menu.NONE, 8, 0, "push_up_out");
        menu.add(Menu.NONE, 9, 0, "shake");
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mode = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public class MainAdapter extends ArrayAdapter<String> {
        private Context context;
        private LayoutInflater mInflater;
        private ArrayList<String> strings;

        private class Holder {
            public TextView textview;
        }

        public MainAdapter(Context context, ArrayList<String> strings) {
            super(context, 0, strings);
            this.context = context;
            this.mInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.strings = strings;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            final String str = this.strings.get(position);
            final Holder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item, null);

                holder = new Holder();
                holder.textview = (TextView) convertView.findViewById(R.id.list_item_text);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            //holder.textview.setText(str);
            Animation animation = null;

            switch (mode) {
                case 1:
                    animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
                    break;
                case 2:
                    animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_in);
                    break;
                case 3:
                    animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_out);
                    break;
                case 4:
                    animation = AnimationUtils.loadAnimation(context, R.anim.wave_scale);
                    break;
                case 5:
                    animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
                    break;
                case 6:
                    animation = AnimationUtils.loadAnimation(context, R.anim.push_left_out);
                    break;
                case 7:
                    animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
                    break;
                case 8:
                    animation = AnimationUtils.loadAnimation(context, R.anim.push_up_out);
                    break;
                case 9:
                    animation = AnimationUtils.loadAnimation(context, R.anim.shake);
                    break;
            }

            convertView.startAnimation(animation);
            return convertView;
        }

    }
}
