package com.demo.liuyifeng.remoteview_notification;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Iterator;

/**
 * Created by liuyifeng on 15-1-20.
 */
public class MemoryCleaner {
    private Context mContext;
    private View mNotiView;
    private View mNotiLayout;

    private ObjectAnimator mTranslationYAnim;
    private ObjectAnimator mTranslationYRAnim;
    private ObjectAnimator mAlphaAnim;
    private ObjectAnimator mAlphaRAnim;
    private AnimatorSet mAnimatorSet;

    private boolean mIsShowing = false;

    public MemoryCleaner(Context context) {
        mContext = context;
        mNotiView = LayoutInflater.from(context).inflate(R.layout.quickdialog, null);
        mNotiLayout = mNotiView.findViewById(R.id.quick_layout);
    }

    public void clearMemory() {
        long free = Util.getFreeMemoryByM();
        clear();
        long newFree = Util.getFreeMemoryByM();

        showNotification(newFree - free);
    }

    private void clear() {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        Iterator iterator = am.getRunningAppProcesses().iterator();

        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo processInfo =
                    (ActivityManager.RunningAppProcessInfo) iterator.next();

            if (processInfo.processName.startsWith("android") ||
                    processInfo.processName.equals("system") ||
                    processInfo.processName.equals("com.android.alarmclock") ||
                    processInfo.processName.equals("com.android.phone") ||
                    processInfo.processName.equals("com.google.process.gapps") ||
                    processInfo.processName.equals("android.process.acore")) {
                continue;
            }

            am.killBackgroundProcesses(processInfo.pkgList[0]);
        }
    }

    private void showNotification(long cleared) {
        if (mIsShowing) {
            return;
        }

        mIsShowing = true;
        ((TextView) mNotiLayout.findViewById(R.id.quickmem)).setText("" + cleared + "M");

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.format = PixelFormat.RGBA_8888;
        params.width = 800;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        wm.addView(mNotiView, params);

        animate();
    }

    private void animate() {
        Animator[] animators;

        mAnimatorSet = new AnimatorSet();

        mTranslationYAnim = ObjectAnimator.ofFloat(mNotiLayout, "translationY", new float[]{-350f, 0f});
        mTranslationYAnim.setDuration(300);

        mAlphaAnim = ObjectAnimator.ofFloat(mNotiLayout, "alpha", new float[]{0f, 1f});
        mAlphaAnim.setDuration(300);

        mTranslationYRAnim = ObjectAnimator.ofFloat(mNotiLayout, "translationY", new float[]{0f, -400f});
        mTranslationYRAnim.setDuration(300);
        mTranslationYRAnim.setStartDelay(2400);

        mAlphaRAnim = ObjectAnimator.ofFloat(mNotiLayout, "alpha", new float[]{1f, 0.1f});
        mAlphaRAnim.setDuration(300);
        mAlphaRAnim.setStartDelay(2400);

        if (Build.VERSION.SDK_INT >= 16) {
            animators = new Animator[4];
            animators[0] = this.mAlphaAnim;
            animators[1] = this.mTranslationYAnim;
            animators[2] = this.mTranslationYRAnim;
            animators[3] = this.mAlphaRAnim;

            mAnimatorSet.playTogether(animators);
        } else {
            animators = new Animator[2];
            animators[0] = this.mTranslationYAnim;
            animators[1] = this.mTranslationYRAnim;

            mAnimatorSet.playTogether(animators);
        }

        this.mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                wm.removeView(mNotiView);
                mIsShowing = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mAnimatorSet.start();
    }
}
