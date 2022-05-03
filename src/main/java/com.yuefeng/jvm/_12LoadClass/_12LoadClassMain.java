package com.yuefeng.jvm._12LoadClass;

/**
 * 类的主动使用和被动使用
 */
public class _12LoadClassMain {

    public static void main(String[] args) {
        System.out.println(Son.k);
        System.out.println(Son.y);

        Son.print();
    }

}
