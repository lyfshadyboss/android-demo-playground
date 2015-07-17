package com.example.yifengliu.test;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

/**
 * Created by yifengliu on 15-7-13.
 */
public class Test6 extends FrameLayout {
    View testView;
    TranslateAnimation anim;

    public Test6(Context context) {
        super(context);
    }

    public Test6(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test6(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        testView = findViewById(R.id.test6_view);

        testView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                anim = new TranslateAnimation(0, 0, 0, -500);
                anim.setDuration(1000);
                anim.setInterpolator(new DecelerateInterpolator());

                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.d("lyftest", "anim start");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d("lyftest", "anim end");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.d("lyftest", "anim repeat");
                    }
                });

                testView.startAnimation(anim);

                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getContext(), Activity2.class);
                        getContext().startActivity(intent);
                    }
                }, 300);
            }
        });
    }

    protected void onResume() {
    }

    protected void onPause() {
    }

    protected void onStop() {
        //testView.clearAnimation();
    }
}
