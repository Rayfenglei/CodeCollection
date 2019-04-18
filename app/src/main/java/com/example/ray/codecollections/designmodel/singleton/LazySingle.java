package com.example.ray.codecollections.designmodel.singleton;
/*
 * 1、私有构造函数
 * 2、提供本类类型的私有静态对象
 * 3、提供方法暴露对象
 *
 *  懒汉式
 *  线程安全
 *  优点：第一次调用才初始化，避免内存浪费。
 *  缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 * */
public class LazySingle {
    //
    private static LazySingle lazySingle;

    private LazySingle(){

    }

    private static synchronized LazySingle getInstance(){
        if (lazySingle == null)
        {
            lazySingle = new LazySingle();
        }
        return lazySingle;
    }
}
