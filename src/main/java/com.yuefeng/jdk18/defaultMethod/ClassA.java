package com.yuefeng.jdk18.defaultMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/26
 */
public class ClassA<T> implements InterfaceTest<T>{
    @Override
    public void printInfo(T t) {
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
//        InterfaceTest classA = new ClassA();
        List<String> list = new ArrayList<>();
        list.add("aaaaaaaaaaa");
        InterfaceTest.printStatic();
        System.out.println("###############");
        InterfaceTest<String> testOrigin = new InterfaceTest<String>() {
            @Override
            public void printInfo(String s) {
                System.out.println("bbbbbb");
            }
        };

        // 函数式变成，直接指定实现唯一的抽象方法
        InterfaceTest<String> testLambada = s -> System.out.println("bbbbbb");


    }
}
