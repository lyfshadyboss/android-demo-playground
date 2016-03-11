package com.example.yifengliu.dagger2demo.component;

import com.example.yifengliu.dagger2demo.MainApplication;
import com.example.yifengliu.dagger2demo.module.ApiModule;
import com.example.yifengliu.dagger2demo.module.MainModule;
import com.example.yifengliu.dagger2demo.view.activity.MainActivity;
import com.example.yifengliu.dagger2demo.view.activity.ReposListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 组件
 * <p>
 * Created by yifengliu on 16/3/8.
 */
@Singleton
@Component(modules = {MainModule.class, ApiModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);

    void inject(ReposListActivity reposListActivity);

    final class Initializer {
        // 初始化组件
        public static MainComponent init(MainApplication app) {
            return DaggerMainComponent.builder()
                    .mainModule(new MainModule(app))
                    .build();
        }
    }
}
