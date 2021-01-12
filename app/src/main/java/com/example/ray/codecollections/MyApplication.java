package com.example.ray.codecollections;

import android.app.Application;
import android.content.Context;

public class MyApplication  extends Application {
    private static MyApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //这是测试代码
    }

    public static Context getContext() {
        return context;
    }

}