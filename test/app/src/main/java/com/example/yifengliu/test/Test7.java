package com.example.yifengliu.test;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yifengliu on 15-7-17.
 */
public class Test7 extends FrameLayout {
    ListView listView;
    MyAdater adapter;

    private List<View> transitionViewList = new ArrayList<View>();

    private static final String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"
    };

    public Test7(Context context) {
        super(context);
    }

    public Test7(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test7(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        listView = (ListView) findViewById(R.id.test7_listview);

        List<String> strList = new ArrayList<>();
        for (int i = 0; i < strs.length; ++i) {
            strList.add(strs[i]);
        }

        adapter = new MyAdater();
        adapter.setElements(strList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exitTransition(parent, position, 500);

                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getContext(), Activity2.class);
                        getContext().startActivity(intent);
                    }
                }, 500);
            }
        });
    }


    private void exitTransition(AdapterView<?> adapterView, int position, int duration) {
        int firstPosition = adapterView.getFirstVisiblePosition();
        int endPosition = adapterView.getLastVisiblePosition();

        for (int i = firstPosition; i <= endPosition; i++) {
            int index = i - firstPosition;
            View itemView = adapterView.getChildAt(index);
            if (i < position) {
                AnimationUtil.getInstance().hideViewWithAlpha(itemView, AnimationUtil.DOWN_TO_TOP, duration, 200, true);
                transitionViewList.add(itemView);
            } else if (i > position) {
                AnimationUtil.getInstance().hideViewWithAlpha(itemView, AnimationUtil.TOP_TO_DOWN, duration, 200, true);
                transitionViewList.add(itemView);
            }
        }
    }

    class MyAdater extends BaseAdapter {

        List<String> elements = new ArrayList<>();

        public void setElements(List<String> elements) {
            this.elements = elements;
        }

        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflate(getContext(), R.layout.my_list_view_item, null);
            }

            TextView text = (TextView) convertView.findViewById(R.id.text);
            text.setText(elements.get(position));

            return convertView;
        }
    }


    protected void onResume() {
        List<String> newStrList = new ArrayList<>();
        for (int i = 0; i < strs.length; ++i) {
            newStrList.add(strs[i]);
        }

        adapter = new MyAdater();
        adapter.setElements(newStrList);
        listView.setAdapter(adapter);

        //listView.clearDisappearingChildren();
        for (View view : transitionViewList) {
            view.clearAnimation();
        }
        transitionViewList.clear();
    }

    protected void onPause() {
    }

    protected void onStop() {
    }
}
