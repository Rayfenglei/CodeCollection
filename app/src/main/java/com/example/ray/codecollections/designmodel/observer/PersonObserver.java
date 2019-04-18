package com.example.ray.codecollections.designmodel.observer;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/*
* observer 观察者
* */
public class PersonObserver implements Observer {
    private int id;
    public PersonObserver(int id){
        this.id = id;
    }
    @Override
    public void update(Observable observable, Object o) {
        Log.i("observermodel","观察者 "+id+"--> "+observable+" 数据更新："+ o.toString());
    }
}
