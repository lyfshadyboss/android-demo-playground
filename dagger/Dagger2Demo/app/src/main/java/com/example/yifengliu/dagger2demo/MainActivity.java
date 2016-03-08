package com.example.yifengliu.dagger2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yifengliu.dagger2demo.list.ReposListActivity;

import butterknife.ButterKnife;

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

        DemoApplication.component().inject(this); // 应用注入
    }

    // 跳转列表视图
    public void gotoReposList(View view) {
        startActivity(new Intent(this, ReposListActivity.class));
    }
}
