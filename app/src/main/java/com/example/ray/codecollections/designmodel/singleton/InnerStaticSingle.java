package com.example.ray.codecollections.designmodel.singleton;
/*
 * 1、私有构造函数
 * 2、提供本类类型的私有静态对象
 * 3、提供方法暴露对象
 *
 * 静态内部类
 * 线程安全
 * 推荐使用
 *
 * */
public class InnerStaticSingle {
    private InnerStaticSingle(){

    }

    private static class InnerClass{
        private static InnerStaticSingle STATICSINGLE = new InnerStaticSingle();
    }

    public static InnerStaticSingle getInstance(){
        return InnerClass.STATICSINGLE;
    }
}
