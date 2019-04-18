package com.example.ray.codecollections.designmodel.observer;

import java.util.Observable;
/*
* Observable 被观察者
* */
public class PersonObservable extends Observable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.setChanged();//让对象改变（这个必须设置：手动通知对象改变了）
        this.notifyObservers(age);// 通知观察者
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.setChanged();
        this.notifyObservers(name);
        this.name = name;
    }
}
