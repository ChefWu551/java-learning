package com.yuefeng.jvm;

/**
 * 查证string pool真实存在，且字符串常量池不会重复
 * 通过idea的debug模式下，查看memory列表里面的java.lang.string个数
 */
public class _07StringTableMemory {

    public static void main(String[] args) {
        String a = "1"; // 1173个
        String b = "2"; // 1174个
        String c = "3";
        String d = "4";
        String e = "5";

        String f = "4"; // 1178个
        String i = "5"; // 1179个
    }
}
