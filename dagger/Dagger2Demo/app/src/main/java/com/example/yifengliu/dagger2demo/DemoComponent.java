package com.example.yifengliu.dagger2demo;

import com.example.yifengliu.dagger2demo.modules.ApiModule;
import com.example.yifengliu.dagger2demo.modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 组件
 * <p>
 * Created by yifengliu on 16/3/8.
 */
@Singleton
@Component(modules = {MainModule.class, ApiModule.class})
public interface DemoComponent extends DemoGraph {
    final class Initializer {
        private Initializer() {
        } // No instances.

        // 初始化组件
        public static DemoComponent init(DemoApplication app) {
            return DaggerDemoComponent.builder()
                    .mainModule(new MainModule(app))
                    .build();
        }
    }
}
