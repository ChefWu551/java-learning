package com.yuefeng.jvm;

/**
 * 字符串常量池，string pool
 * 不会存在一致的字符串值
 * 是一块固定大小的数组链表，可以通过 jinfo -flag stringTableSize查看,不同版本jdk默认不一样
 *      jinfo -flag StringTableSize 1155
 *      -XX:StringTableSize=60013(jdk1.8)
 * 对string pool值在堆中加入的两种方式：
 *      直接用双引号声明复制
 *      调用intern方法
 */
public class _06StringTaleTest {

    public static void main(String[] args) throws Exception{
//        question01();
        question02();
//        question03();
//        Thread.sleep(1000000);
    }

    /**
     * string intern() 方法的使用及原理
     */
    public static void question03() {
        String a = "aa";
        String b = new String("aa");
        b.intern();
        System.out.println(a == b); // false

        // 注意：在new String("cc") 会生成两个对象，一个string对象，一个ldc cc，此时常量池就会多出一个cc字符串
        // 而调用intern其实是无效的，因为此时常量池已有cc字符串
        // c -> 堆空间实例变量c -> 字符串常量的cc
        // d -> 字符串常量的cc
        String c = new String("cc"); // new关键字在堆空间创建的 c -> 堆空间实例变量c -> 字符串常量的cc
        c.intern();
        String d = "cc"; //
        System.out.println(c == d); // false

        // 注意，此时未调用intern前，字符串常量池是没有ee的
        // 在调用intern后会有ee，此时 e -> 字符串常量池ee【参考_08StringTableObjectCount】
        // f -> 字符串常量池ee
        String e = new String("e") + new String("e");
        // 在字符常量池池生成ee，
        // jdk6：创建了一个新的对象，也就有新的地址；
        // jdk7+：常量池位于堆空间，若在字符串常量池之前已经有了该对象值，此时通过调用intern方法，可以使字符串常量池代表值得指针指向该对象地址
        // 所以顺序是 f -> 常量池代表ee的地址 -> 实例对象e，参考 image/stringTable-intern方法.png 图示
        e.intern();
        String f = "ee"; // 使用的是字符串常量池ee的地址
        System.out.println(e == f); // jdk7+ true; jdk6 false[因为字符串常量池在永久代中，也就是非堆区域]

        // 注意，若把代码: e.intern(); 和 String f = "ee"; 顺序对调，则输出false，因为此时string pool字符串已经存在
    }


    /**
     * 带变量字符串拼接，在字节码文件中用了stringBuilder.append 与 普通非常常亮字符串拼接
     * final类型变量拼接同普通类型字符串拼接示例展示
     */
    public static void question02() {
        String a = "1";
        String b = "1";
        String c = "1" + "1";
        String d = a + "1";
        String f = a + b;
        String str = "11";

        // 查看编译后的文件" String c = "11"; " 此处在编译后已经优化成了"11"
        System.out.println(str == c); // true
        // 带有非final类的变量，字符串相加的时候，会通过stringBuilder.append来合并的，最后通过StringBuild.toString方法输出结果
        // 关于字节码文件：new #5 <java/lang/StringBuilder>
        System.out.println(str == d); // false
        System.out.println(str == f); // false
        // final返回true,因为声明为final类型的
        // 字节码解释k值： ldc #4 <11> 指向的是字符串常量池地址为#4的位置，而地址#4的就是str的指针位置
        final String m = "1";
        final String n = "1";
        String k = m + n;
        System.out.println(str == k);
    }

    /**
     * 双引号赋值的字符串 与 通过string对象类型数据实例化的存储位置区别
     * 字节码赋值：四个字符串实例对象指向的都是同一个地址 #14
     *       ldc #14 <test>
     *
     */
    public static void question01() {

        String a = "test";
        String b = "test";

        String aObject = new String("test");
        String bObject = new String("test");

        // jdk7以后，string的值都是存储在常量池中的，常量池存储于堆中，所以此时返回的时true，因为两个string变量指向的是同一个常量池列表的值
        System.out.println(a == b); // true
        System.out.println("a地址："+ a.hashCode() + " b地址： " + b.hashCode()); // 指针地址全部一样
        // 通过new的方法声明的是两个对象，均存放在堆中，指向的对象指针不一样，所以返回false
        System.out.println(aObject == bObject); // false
        System.out.println("a对象地址："+ aObject.hashCode() + " b对象地址： " + bObject.hashCode());
        // 一个指向的是字符串常量池的地址，一个是堆中字符串指针地址
        System.out.println(aObject == a); // false
    }


}
