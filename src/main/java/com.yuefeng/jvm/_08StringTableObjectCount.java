package com.yuefeng.jvm;

/**
 * 在重复的字符串比较多的情景下，通过调用intern方法可以极大的提高执行效率，1000W个对象中有大量重复的字符串
 * 原理：尽可能使用字符串常量池，字符串常量池是不存在重复的字符串的，节省大量空间，且会让gc次数变少
 */
public class _08StringTableObjectCount {

    /**
     * 使用string实例化对象对应的对象创建数量
     * @param args
     */
    public static void main(String[] args) {

        // 两个对象
        //   new #2 <java/lang/String>
        //    ldc #3 <ab>
        String ab = new String("ab");

        // 6个
        //      new #5 <java/lang/StringBuilder>
        //      new #2 <java/lang/String>
        //      ldc #7 <a>
        //      new #2 <java/lang/String>
        //      ldc #9 <b>
        //       invokevirtual #10 <java/lang/StringBuilder.toString> -> stringBuilder.toString();里面又new了一个string对象
        String c = new String("a") + new String("b");

//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.toString();
    }
}
