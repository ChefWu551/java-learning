package com.yuefeng.jvm;

/**
 * 设置栈的大小：-Xss256k -XX:+PrintGCDetails
 *
 */
public class _02StackOverflowTest {
    private static int count = 1;

    public static void main(String[] args) throws Exception{
        System.out.println(count);
        count++;
//        Thread.sleep(1000);
        main(args);
    }
}
