package com.example.yifengliu.fragmentlifecycle;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.yifengliu.fragmentlifecycle.fragment.FragmentA;
import com.example.yifengliu.fragmentlifecycle.fragment.FragmentHelper;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        FragmentA fragmentA = new FragmentA();
        FragmentHelper.initFragment(getSupportFragmentManager(), fragmentA, R.id.container_a, fragmentA.getClass().getSimpleName());
    }
}
