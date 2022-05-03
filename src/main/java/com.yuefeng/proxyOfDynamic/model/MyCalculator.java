package com.yuefeng.proxyOfDynamic.model;

public class MyCalculator implements Calculator {
    @Override
    public int add(int value1, int value2) {
        return value1+value2;
    }

    @Override
    public int div(int value1, int value2) {
        return value1/value2;
    }
}
