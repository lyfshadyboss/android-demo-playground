package com.example.yifengliu.dagger2demo;

import android.app.Application;

import com.example.yifengliu.dagger2demo.component.MainComponent;

/**
 * 应用信息
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public class MainApplication extends Application {
    private static MainGraph sDemoGraph;
    private static MainApplication sInstance;

    public static MainGraph component() {
        return sDemoGraph;
    }

    public static void buildComponentAndInject() {
        sDemoGraph = MainComponent.Initializer.init(sInstance);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        buildComponentAndInject();
    }

}
