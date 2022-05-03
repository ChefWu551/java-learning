package com.yuefeng.jdk18.defaultMethod;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/26
 *
 * jdk1.8功能点 默认方法实现解析
 */

public interface InterfaceTest<T> {

    void printInfo(T t);
//  如果有两个为实现的抽象方法，则没办法使用函数式变成，因为不知道要实现的方法是哪个（匿名函数啦!）
//    void printInfo1(T t);

    default void getAbc() {
        System.out.println("这个默认方法不需要被所有对象实现");
    }

    default void getAbb() {
        System.out.println("这个默认方法不需要被所有对象实现");
    }

    /**
     * 静态方法是可以直接调用的，并且直接通过 ClassName.method方法调用
     * 因为 在jvm编译的时候 静态方法已经被编译完成，linking阶段，所以不需要实例化可直接调用
     * 具体可以通过定义方法及静态代码块、静态方法、初始化方法init()、实例化方法来看调用的顺序
     */
    static void printStatic() {
        System.out.println("静态方法不需要实例化即可调用");
    }
}
