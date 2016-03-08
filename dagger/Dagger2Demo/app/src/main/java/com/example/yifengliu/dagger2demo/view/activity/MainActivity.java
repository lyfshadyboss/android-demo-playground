package com.example.yifengliu.dagger2demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yifengliu.dagger2demo.MainApplication;
import com.example.yifengliu.dagger2demo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主模块, 注册类.
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MainApplication.component().inject(this);
    }

    @OnClick(R.id.go_list_button)
    public void goRepoList(View view) {
        startActivity(new Intent(this, ReposListActivity.class));
    }
}
