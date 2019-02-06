package com.justmobiledev.securepreferenceslib;

import android.app.Application;

public class MyApplication extends Application {

    private static Application instance;

    public static Application getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
