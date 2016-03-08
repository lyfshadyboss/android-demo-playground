package com.example.yifengliu.dagger2demo;

import android.app.Application;

/**
 * 应用信息
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public class DemoApplication extends Application {
    private static DemoGraph sDemoGraph;
    private static DemoApplication sInstance;

    public static DemoGraph component() {
        return sDemoGraph;
    }

    public static void buildComponentAndInject() {
        sDemoGraph = DemoComponent.Initializer.init(sInstance);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        buildComponentAndInject();
    }

}
