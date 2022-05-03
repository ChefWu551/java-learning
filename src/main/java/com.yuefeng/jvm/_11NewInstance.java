package com.yuefeng.jvm;

import java.io.File;

public class _11NewInstance {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
    /**
     * 0 new #2 <java/lang/Object>  new一个对象，对象的引用放置堆中
     * 3 dup    复制一个对象
     * 4 invokespecial #1 <java/lang/Object.<init>> 调用init方法，此时消耗一个对象，把对象的引用指针存放在局部变量表中
     * 7 astore_1   存储一个对象，此时还会再消耗一个对象，所以这就是为什么要复制一个对象的原因
     * 8 new #3 <java/io/File>  new一个对象，此时操作数栈中存储指向该对象地址
     * 11 dup   在操作数中再复制上一个对象，入栈
     * 12 ldc #4 <chef.mp4> 字符串chef.mp4入栈
     * 14 invokespecial #5 <java/io/File.<init>>    调用初始化方法，消耗一个file对象，chef.mp4也会出栈
     * 17 astore_2  引用存储，消耗另一个file对象
     * 18 return
     */
    public void instance() {
        Object o = new Object();

        File file = new File("chef.mp4");
    }

    /**
     *  0 new #6 <com/yuefeng/jvm/Order>
     *  3 dup
     *  4 invokespecial #7 <com/yuefeng/jvm/Order.<init>>
     *  7 astore_1
     *  8 aload_1   实例对象指针，压入操作数栈
     *  9 sipush 1001   数值1001压入操作数栈
     * 12 putfield #8 <com/yuefeng/jvm/Order.id>    出栈，并且把实例数据的id直接指向值1001
     * 15 getstatic #9 <java/lang/System.out>   静态字段，入栈操作
     * 18 aload_1   实例对象order入栈
     * 19 getfield #8 <com/yuefeng/jvm/Order.id>    order.id值入栈
     * 22 invokevirtual #10 <java/io/PrintStream.println>   执行方法，此时order对象，order.id的值，system.out对象均出栈
     * 25 ldc #11 <orderName>   字符串指针 orderName 入操作数栈
     * 27 putstatic #12 <com/yuefeng/jvm/Order.name>    出栈，放入局部变量表，指向对象的属性值
     * 30 getstatic #9 <java/lang/System.out>   入栈
     * 33 getstatic #12 <com/yuefeng/jvm/Order.name>    入栈
     * 36 invokevirtual #13 <java/io/PrintStream.println>   执行方法，出栈
     * 39 return
     */
    public void orderInstance() {
        Order order = new Order();
        order.id = 1001;
        System.out.println(order.id);

        Order.name = "orderName";
        System.out.println(Order.name);
    }

    public void byteAndByte() {
        byte[] b = new byte[1024 * 1024 * 5];

        Byte[] bObject = new Byte[1024 * 1024 * 5];
    }
}

class Order{
    int id;
    static String name;
}