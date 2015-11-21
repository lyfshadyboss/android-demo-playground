package com.example.yifengliu.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by yifengliu on 15/11/21.
 */
public class Test10 extends FrameLayout {
    private static int ITEM_MIN_WIDTH = DeviceInfoUtil.getPixelFromDip(50);
    private static int ITEM_SPACING = DeviceInfoUtil.getPixelFromDip(11);
    private static int ITEM_LINE_COUNT = 2;

    private int itemWidth;
    private int itemCountPerLine;


    private FlowLayout flowLayout;

    public Test10(Context context) {
        super(context);
    }

    public Test10(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test10(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        detectItemWidth();
        flowLayout = (FlowLayout) findViewById(R.id.flow_layout);

        for (int i = 0; i < 10; i++) {
            View view = new View(getContext());
            view.setBackgroundResource(android.R.color.holo_green_light);
            FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(itemWidth, 80);

            if (i % itemCountPerLine != (itemCountPerLine - 1)) {
                lp.setMargins(0, 0, ITEM_SPACING, ITEM_SPACING);
            } else {
                lp.setMargins(0, 0, 0, ITEM_SPACING);
            }

            flowLayout.addView(view, lp);
        }
    }

    private void detectItemWidth() {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int availableWidth = metrics.widthPixels - getPaddingLeft() - getPaddingRight();
        itemCountPerLine = 4;
        while (itemCountPerLine > 0) {
            itemWidth = (availableWidth - (itemCountPerLine - 1) * ITEM_SPACING) / itemCountPerLine;
            if (itemWidth < ITEM_MIN_WIDTH) {
                itemCountPerLine--;
            } else {
                break;
            }
        }
    }
}
