package com.example.yifengliu.fragmentlifecycle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yifengliu.fragmentlifecycle.LogHelper;

/**
 * Created by yifengliu on 16/3/24.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        LogHelper.d(this, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogHelper.d(this, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogHelper.d(this, "onCreateView");

        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LogHelper.d(this, "onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LogHelper.d(this, "onActivityCreated");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        LogHelper.d(this, "onHiddenChanged : " + hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LogHelper.d(this, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LogHelper.d(this, "onDestroy");
    }
}
