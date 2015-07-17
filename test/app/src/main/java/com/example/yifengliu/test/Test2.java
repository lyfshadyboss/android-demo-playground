package com.example.yifengliu.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by yifengliu on 15-5-4.
 */
public class Test2 extends LinearLayout {

    TextView mytext;
    Button mybutton;
    ListView mylistview;

    private static final String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh"
    };

    public Test2(Context context) {
        super(context);
    }

    public Test2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mytext = (TextView) findViewById(R.id.mytext);
        mybutton = (Button) findViewById(R.id.mybutton);
        mylistview = (ListView) findViewById(R.id.mylistview);
        mylistview.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strs));
//
//        ViewHelper.setTranslationY(mylistview, 300);
//        ViewHelper.setTranslationY(mybutton, 300);

//        LinearLayout test2 = (LinearLayout) findViewById(R.id.test2);
//        TextView text = new TextView(getContext());
//        text.setText("HHHHHHHHHHHHHHHHHHHHHHHH");
//        test2.addView(text, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
