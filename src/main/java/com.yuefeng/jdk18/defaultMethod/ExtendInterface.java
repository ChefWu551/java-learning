package com.yuefeng.jdk18.defaultMethod;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/26
 */
public interface ExtendInterface<T> extends InterfaceTest<T>{

    @Override
    void printInfo(T t) ;

    // 默认方法是可以被继承的！！！
    @Override
    void getAbb() ;
}
