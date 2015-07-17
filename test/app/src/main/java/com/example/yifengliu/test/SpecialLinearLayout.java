package com.example.yifengliu.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by yifengliu on 15-5-12.
 */
public class SpecialLinearLayout extends LinearLayout {
    public SpecialLinearLayout(Context context) {
        super(context);
    }

    public SpecialLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpecialLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        if (count >= 3) {
            View first = getChildAt(0);
            View last = getChildAt(count - 1);

            int space = 0;
            for (int i = 0; i < count; ++i) {
                View view = getChildAt(i);
                if (view != first && view != last) {
                    space += view.getMeasuredWidth();
                }
            }

            if (first.getMeasuredWidth() > (getMeasuredWidth() - space) / 2) {
                first.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - space) / 2, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(first.getMeasuredHeight(), MeasureSpec.UNSPECIFIED));

                for (int j = 1; j < count - 1; j++) {
                    View child = getChildAt(j);
                    child.measure(MeasureSpec.makeMeasureSpec(child.getMeasuredWidth(), MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), MeasureSpec.EXACTLY));
                }

                last.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - space) / 2, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(first.getMeasuredHeight(), MeasureSpec.UNSPECIFIED));
            }
        }
    }
}
