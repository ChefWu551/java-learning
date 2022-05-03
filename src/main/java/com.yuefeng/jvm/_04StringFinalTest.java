package com.yuefeng.jvm;

// todo：解释一下为什么会这样

/**
 * jdk8- 使用的是char数组来存储string
 * jdk9+ 使用的是byte数组来存储string，因为针对大部分的字符，拉丁文和iso9986用八位存储就够了，不需要两个字节
 */
public class _04StringFinalTest {

    public static String str = "good";

    public char[] chars = {'a', 'b', 'c'};

    public void changeValue(String str, char[] chars) {
        str = "change";
        chars[0] = 'd';
    }

    public static void main(String[] args) {
        _04StringFinalTest test = new _04StringFinalTest();
        test.changeValue(str, test.chars);
        System.out.println(str);
        System.out.println(test.chars);

        str = "main change";
        System.out.println(str);

        Integer i = 1000;
        Integer k = 1000;
        System.out.println(i == k); // false, 对象存储，所以指针是不一样的

        String m = "123";
        String n = "123";
        System.out.println(m == n); // true

        String q = new String("123");
        String p = new String("123");
        System.out.println(q == p); // false
    }
}
