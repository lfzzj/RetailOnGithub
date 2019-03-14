package com.leo.java.myretailapps;

import android.app.Application;

import interfaces.heweather.com.interfacesmodule.view.HeConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HeConfig.init("HE1903131150551130", "fafc201eadbf4fe3a0789d4e0ae5a259");
        HeConfig.switchToFreeServerNode();
    }
}
