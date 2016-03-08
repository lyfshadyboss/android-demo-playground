package com.example.yifengliu.dagger2demo;


import com.example.yifengliu.dagger2demo.view.activity.MainActivity;
import com.example.yifengliu.dagger2demo.view.activity.ReposListActivity;

/**
 * 图信息
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public interface MainGraph {
    void inject(MainActivity mainActivity); // 注入MainActivity

    void inject(ReposListActivity reposListActivity); // 注入列表Activity
}
