package com.yuefeng.proxyOfDynamic;

import com.yuefeng.proxyOfDynamic.model.MyCalculator;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

public class CreateObject {

    @SneakyThrows
    public static void main(String[] args) {
        // 直接实例化
        MyCalculator calculator = new MyCalculator();

        // 直接用当前类初始化
        MyCalculator calculator1 = MyCalculator.class.newInstance();

        Class calculatorClass = Class.forName("com.yuefeng.proxyOfDynamic.model.MyCalculator");
        // 反射调用全类名，直接初始化
        calculatorClass.newInstance();

        // 反射获取当前类，构造方法实例化
        Constructor calculator3 = calculatorClass.getDeclaredConstructor();
        calculator3.newInstance();


    }
}
