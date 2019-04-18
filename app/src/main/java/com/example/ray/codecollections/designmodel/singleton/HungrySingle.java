package com.example.ray.codecollections.designmodel.singleton;
/*
* 1、私有构造函数
* 2、提供本类类型的私有静态对象
* 3、提供方法暴露对象
*
* 饿汉式
* 线程安全
* 优点：没有加锁，执行效率会提高。
* 缺点：类加载时就初始化，浪费内存。
* */
public class HungrySingle {
    //提供本类类型的私有静态对象
    private static HungrySingle hungrySingle = new HungrySingle();
    //私有构造函数
    private HungrySingle(){

    }
    //提供方法暴露对象
    public static HungrySingle getInstance(){
        return hungrySingle;
    }
}
