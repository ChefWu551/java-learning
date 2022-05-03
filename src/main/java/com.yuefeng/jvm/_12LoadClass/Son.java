package com.yuefeng.jvm._12LoadClass;

public class Son extends Father {

    static {
        System.out.println("son static code work");
    }
    public Son() {
        System.out.println("son constructor work");
    }

    public static void print() {
        System.out.println("son static method work");
    }
}
