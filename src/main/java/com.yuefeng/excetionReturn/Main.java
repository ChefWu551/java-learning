package com.yuefeng.excetionReturn;

public class Main {

    public static String getStringWithoutException() {
        String value = "1234";
        try {
            value = value + " try ";
            return value;
        } finally {
            return value + " finally ";
        }
    }

    public static String getStringWithException() {
        String value = "1234";
        try {
            return value + " try ";
        } finally {
            value = value + " finally ";
        }
    }

    public static Integer getValueWithoutString() {
        Integer value = 10;
        try {
            System.out.println("地址是1：" + value.hashCode());
            return value;
        } finally {
            value = 4;
            System.out.println("地址是2：" + value.hashCode());
            System.out.println("无异常执行finally返回值：" + value);
        }
    }

    public static Integer getValueWithException() {
        int value = 10;
        try {
            value = 5;
            return value+1;
        } finally {
            return value+2;
        }
    }

    public static void main(String[] args) {
        System.out.println(getStringWithoutException());
        System.out.println("---------");
        System.out.println(getStringWithException());
        System.out.println("---------");
        System.out.println("无异常实际返回值：" + getValueWithoutString());
        System.out.println("---------");
        System.out.println("有异常实际返回值：" + getValueWithException());
        System.out.println("---------");
    }
}
