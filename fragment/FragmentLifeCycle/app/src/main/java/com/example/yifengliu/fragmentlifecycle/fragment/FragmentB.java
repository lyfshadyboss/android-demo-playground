package com.example.yifengliu.fragmentlifecycle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yifengliu.fragmentlifecycle.R;

/**
 * Created by yifengliu on 16/3/24.
 */
public class FragmentB extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View contentView = inflater.inflate(R.layout.fragment_b_layout, null);

        return contentView;
    }
}
