package com.example.yifengliu.test;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by yifengliu on 15-11-3.
 */
public class Test9 extends FrameLayout {

    private static final String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh"
    };

    ListView mylistview;
    ViewPager viewPager;

    public Test9(Context context) {
        super(context);
    }

    public Test9(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mylistview = (ListView) findViewById(R.id.mylistview);

        if (mylistview != null) {
            mylistview.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strs));
        }

        viewPager = (ViewPager) findViewById(R.id.myviewpager);
        viewPager.setAdapter(new SimpleCardAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    class SimpleCardAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View item = new View(getContext());
//            View v = new View(getContext());
//            ((ViewGroup) item).addView(v, new ViewGroup.LayoutParams(100, 100));
            item.setBackgroundColor(Color.BLUE);
            ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
            lp.width = 100;
            lp.height = 100;
            container.addView(item, lp);

            return item;
        }
    }
}
