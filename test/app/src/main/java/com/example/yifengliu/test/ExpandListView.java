package com.example.yifengliu.test;

import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by yifengliu on 15-11-3.
 * 可以嵌套在其他控件之中完全展示出来的listview
 */
public class ExpandListView extends ListView {
    public ExpandListView(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}