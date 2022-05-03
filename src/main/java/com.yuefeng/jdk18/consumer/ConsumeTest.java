package com.yuefeng.jdk18.consumer;

import java.util.function.Consumer;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/3/26
 */
public class ConsumeTest {

    public static void main(String[] args) {
        // consumer是一个消费对象，直接吃掉了入参
        Consumer consumer = s -> System.out.println("返回内容为空的方法");
    }
}
