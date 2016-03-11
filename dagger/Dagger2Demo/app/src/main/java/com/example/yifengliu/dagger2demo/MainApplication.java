package com.example.yifengliu.dagger2demo;

import android.app.Application;

import com.example.yifengliu.dagger2demo.component.MainComponent;

/**
 * 应用信息
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public class MainApplication extends Application {
    private static MainComponent sMainComponent;
    private static MainApplication sInstance;

    public static MainComponent component() {
        return sMainComponent;
    }

    public static void buildComponentAndInject() {
        sMainComponent = MainComponent.Initializer.init(sInstance);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        buildComponentAndInject();
    }

}
