package com.example.yifengliu.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

/**
 * Created by yifengliu on 15-7-27.
 */
public class Test8 extends FrameLayout {
    View text;
    ShadowView shadow;

    public Test8(Context context) {
        super(context);
    }

    public Test8(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test8(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        text = findViewById(R.id.test8_text);
        shadow = (ShadowView) findViewById(R.id.test8_shadow_view);

        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = shadow.getLayoutParams();
                params.width = text.getMeasuredWidth();
                params.height = text.getMeasuredHeight();
                shadow.setAttachedView(text);
                shadow.requestLayout();

                AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                fadeOut.setDuration(1000);
                text.startAnimation(fadeOut);

                TranslateAnimation translate = new TranslateAnimation(0, 0, 0, 500);
                translate.setDuration(1000);
                shadow.startAnimation(translate);
            }
        });
    }
}
