package com.example.yifengliu.fragmentlifecycle.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.yifengliu.fragmentlifecycle.R;

/**
 * Created by yifengliu on 16/3/24.
 */
public class FragmentHelper {

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, String tag) {
        addFragment(fragmentManager, fragment, Window.ID_ANDROID_CONTENT, tag);
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int containerId, String tag) {
        addFragment(fragmentManager, fragment, containerId,
                R.anim.common_anim_fragment_in,
                R.anim.common_anim_fragment_out,
                R.anim.common_anim_fragment_close_in,
                R.anim.common_anim_fragment_close_out,
                tag, true);
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment fragment, String tag) {
        initFragment(fragmentManager, fragment, Window.ID_ANDROID_CONTENT, tag);
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment fragment, int containerId, String tag) {
        addFragment(fragmentManager, fragment, containerId, 0, 0, 0, 0, tag, false);
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int containerId,
                                   int in, int out, int closeIn, int closeOut, String tag, boolean addToBackStack) {
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(in, out, closeIn, closeOut);
            Fragment f = fragmentManager.findFragmentById(containerId);
            if (f != null) {
                if (f instanceof FragmentManager.OnBackStackChangedListener) {
                    fragmentManager.addOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) f);
                }
                transaction.hide(f);
            }
            transaction.add(containerId, fragment, tag);

            if (addToBackStack) {
                transaction.addToBackStack(tag);
            }

            transaction.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
