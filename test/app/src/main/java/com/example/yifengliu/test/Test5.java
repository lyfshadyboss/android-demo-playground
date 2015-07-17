package com.example.yifengliu.test;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by yifengliu on 15-7-7.
 */
public class Test5 extends FrameLayout {

    LinearLayout group1;
    LinearLayout group2;

    public Test5(Context context) {
        super(context);
    }

    public Test5(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        group1 = (LinearLayout) findViewById(R.id.test5_group1);
        group2 = (LinearLayout) findViewById(R.id.test5_group2);

        doAnimation();
    }

    private void doAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(5000);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                group1.setVisibility(View.VISIBLE);
                group2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float factor = (float) animation.getAnimatedValue();
                float transitionY = 500 * (1 - factor);

                setTranslationY(transitionY);
                group1.setAlpha(1 - factor);
                group2.setAlpha(factor);
            }
        });

        animator.start();
    }
}
