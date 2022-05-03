package com.yuefeng.jvm;

public class _11IntegerEqual {

    public static void main(String[] args) {
        Integer a = 5;  // 0 iconst_5
        Integer b = 5;  // 0 iconst_5
        System.out.println(a == b);

        Integer aa = 128;   //  sipush 128; invokestatic #2 <java/lang/Integer.valueOf>;查看valueOf源码可知，这边new Integer操作
        Integer bb = 128;
        System.out.println(aa == bb);
    }

    /* Integer.valueOf源码
    public static Integer valueOf(int i) {
        if (i >= Integer.IntegerCache.low && i <= Integer.IntegerCache.high)
            return Integer.IntegerCache.cache[i + (-Integer.IntegerCache.low)];
        return new Integer(i);
    }*/
}
