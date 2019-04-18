package com.example.ray.codecollections.designmodel.singleton;
/*
 * 1、私有构造函数
 * 2、提供本类类型的私有静态对象
 * 3、提供方法暴露对象
 *
 * 双检锁/双重校验锁
 * 线程安全
 * 推荐使用
 *
 * */
public class DCLSingle {
    //volatile可以保证sInstance对象每次都是从内存中读取
    private volatile static DCLSingle dclSingle;

    private DCLSingle (){

    }

    public static DCLSingle getSingleton() {
        //两次非空判断：第一层主要是为了避免不必要的同步，第二层的判断则是为了在null的情况下创建实例
        if (dclSingle == null) {
            synchronized (DCLSingle.class) {
                if (dclSingle == null) {
                    dclSingle = new DCLSingle();
                }
            }
        }
        return dclSingle;
    }
}
