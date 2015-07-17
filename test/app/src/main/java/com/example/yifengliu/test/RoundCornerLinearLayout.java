package com.example.yifengliu.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by yifengliu on 15-7-1.
 */
public class RoundCornerLinearLayout extends LinearLayout {
    private final RectF zoneRect = new RectF();
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    private float radius = 6;

    public RoundCornerLinearLayout(Context context) {
        super(context);
        init();
    }

    public RoundCornerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundCornerLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        zonePaint.setAntiAlias(true);
        radius = radius * getResources().getDisplayMetrics().density;
    }

    @Override
    public void draw(Canvas canvas) {
        zoneRect.set(0, 0, getWidth(), getHeight());

        //layer 1
        canvas.saveLayer(zoneRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(zoneRect, radius, radius, zonePaint);

        // layer2
        canvas.saveLayer(zoneRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);

        canvas.restore();
        canvas.restore();
    }
}
