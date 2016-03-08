package com.example.yifengliu.dagger2demo.component;

import com.example.yifengliu.dagger2demo.MainApplication;
import com.example.yifengliu.dagger2demo.MainGraph;
import com.example.yifengliu.dagger2demo.module.ApiModule;
import com.example.yifengliu.dagger2demo.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 组件
 * <p>
 * Created by yifengliu on 16/3/8.
 */
@Singleton
@Component(modules = {MainModule.class, ApiModule.class})
public interface MainComponent extends MainGraph {
    final class Initializer {
        private Initializer() {
        } // No instances.

        // 初始化组件
        public static MainComponent init(MainApplication app) {
            return DaggerMainComponent.builder()
                    .mainModule(new MainModule(app))
                    .build();
        }
    }
}
