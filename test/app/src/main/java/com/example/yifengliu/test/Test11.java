package com.example.yifengliu.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by liuyifeng on 15/12/21.
 */
public class Test11 extends LinearLayout {
    private ImageView imageButton;

    public Test11(Context context) {
        super(context);
    }

    public Test11(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Test11(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageButton = (ImageView) findViewById(R.id.image_button);

        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
