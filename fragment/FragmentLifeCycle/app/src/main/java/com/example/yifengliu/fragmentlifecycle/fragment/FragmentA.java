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
public class FragmentA extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View contentView = inflater.inflate(R.layout.fragment_a_layout, null);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragmentB = new FragmentB();
                FragmentHelper.addFragment(getFragmentManager(), fragmentB, R.id.container_b, fragmentB.getClass().getSimpleName());
            }
        });

        return contentView;
    }
}
