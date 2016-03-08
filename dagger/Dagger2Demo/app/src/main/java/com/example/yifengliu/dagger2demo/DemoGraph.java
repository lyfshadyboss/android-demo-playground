package com.example.yifengliu.dagger2demo;


import com.example.yifengliu.dagger2demo.list.ReposListActivity;

/**
 * 图信息
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public interface DemoGraph {
    void inject(MainActivity mainActivity); // 注入MainActivity

    void inject(ReposListActivity reposListActivity); // 注入列表Activity
}
