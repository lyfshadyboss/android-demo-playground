package com.example.yifengliu.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by yifengliu on 15-11-3.
 */
public class Test9 extends FrameLayout {

    private static final String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh"
    };

    ListView mylistview;

    public Test9(Context context) {
        super(context);
    }

    public Test9(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mylistview = (ListView) findViewById(R.id.mylistview);

        if (mylistview != null) {
            mylistview.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strs));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
