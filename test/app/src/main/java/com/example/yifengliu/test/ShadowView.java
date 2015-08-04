package com.example.yifengliu.test;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yifengliu on 15-7-27.
 */
public class ShadowView extends View {
    private View attachedView;

    public ShadowView(Context context) {
        super(context);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public View getAttachedView() {
        return attachedView;
    }

    public void setAttachedView(View attachedView) {
        this.attachedView = attachedView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (attachedView != null) {
            attachedView.draw(canvas);
        }
    }
}
