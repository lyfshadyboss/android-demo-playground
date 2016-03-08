package com.example.yifengliu.dagger2demo.modules;

import android.app.Application;
import android.content.res.Resources;

import com.example.yifengliu.dagger2demo.DemoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 主要模块, 提供Application和resources.
 * <p>
 * Created by yifengliu on 16/3/8.
 */
@Module
public class MainModule {
    private final DemoApplication mApp;

    public MainModule(DemoApplication application) {
        mApp = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApp.getResources();
    }
}