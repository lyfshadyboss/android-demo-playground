package com.example.yifengliu.pathanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TestView extends ImageView {
    private Paint paint;

    private Anchor start;
    private Anchor middle;
    private Anchor end;
    private Anchor current;

    private boolean isAnchorsInited = false;
    private boolean isArriveMiddle = false;

    private ValueAnimator animator;

    public TestView(Context context) {
        super(context);
        initView();
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.rgb(0xE4, 0xe4, 0xe9));
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);

        start = new Anchor();
        middle = new Anchor();
        end = new Anchor();
        current = new Anchor();

        animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();

                if (fraction < 0.5f) {
                    current.x = start.x + (middle.x - start.x) * fraction * 2;
                    current.y = start.y + (middle.y - start.y) * fraction * 2;
                } else {
                    isArriveMiddle = true;
                    current.x = middle.x + (end.x - middle.x) * (fraction - 0.5f) * 2;
                    current.y = middle.y + (end.y - middle.y) * (fraction - 0.5f) * 2;
                }

                TestView.this.invalidate();
            }
        });

        animator.setStartDelay(500);
        animator.start();
    }

    private void initAnchors(int width, int height) {
        float r = width / 2f;
        float l = r * 3 / 5;
        float cos45 = (float) Math.cos(Math.PI / 4);

        float deltaX = r / 4;
        float deltaY = 0;

        start.x = r - l / cos45 + deltaX;
        start.y = r + deltaY;

        middle.x = r - l * cos45 + deltaX;
        middle.y = r + l * cos45 + deltaY;

        end.x = r + l * cos45 + deltaX;
        end.y = r - l * cos45 + deltaY;

        current.x = start.x;
        current.y = start.y;

        isAnchorsInited = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isAnchorsInited) {
            initAnchors(getWidth(), getHeight());
        }

        if (!isArriveMiddle) {
            canvas.drawLine(start.x, start.y, current.x, current.y, paint);
        } else {
            canvas.drawLine(start.x, start.y, middle.x, middle.y, paint);
            canvas.drawLine(middle.x, middle.y, current.x, current.y, paint);
        }
    }

    private class Anchor {
        float x;
        float y;

        public Anchor() {
            this.x = 0f;
            this.y = 0f;
        }

        public Anchor(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
