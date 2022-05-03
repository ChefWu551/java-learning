package com.yuefeng.jvm;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;


/**
 * 类的主动使用，看类是否会导致类的初始化，既clinit()方法，clinit方法会将jvm类加载准备阶段赋值的默认值修改成对应实际的值
 *      1.  创建类的实例，new对象
 *      2.  访问类或者接口的静态变量，或对该静态变量赋值
 *      3.  调用类的静态方法
 *      4.  通过反射操作这个类
 *      5.  初始化一个类的子类，这个类也会被跟着初始化
 *      6.  Java虚拟机启动时被标记为启动类的类
 *      7.  jdk1.7以后支持动态语言
 *
 * 被动使用：
 *      1.  子对象引用父对象的静态字段不会导致子对象初始化
 *      2.  引用类的常量信息 eg: static final 类型的常量
 *      3.  通过数组来引用类
 */
public class _097CLinit {

    @SneakyThrows
    public static void main(String[] args) {
        // 主动引用
        // 直接引用类变量=>只会执行静态方法
//        System.out.println(Father.code);

        // 引用子类的类变量-子类自己没有=>子类本身是不会被触发的，只会执行父类静态方法
//        System.out.println(Child.name);

        // 引用子类的类变量-子类自己有=>父类静态方法-子类静态方法
//        System.out.println(Child.code);

        // Class.forName("com.yuefeng.jvm.Father") 不会导致构造方法执行
        /*Class<?> father = Class.forName("com.yuefeng.jvm.Father");
        Method method = father.getDeclaredMethod("printStr");*/

        // 会
        // 父类的静态方法，构造方法都会被执行，此时会有init方法
        /*Father father = new Father();
        System.out.println(father.sex);*/

        // 执行顺序依次是：父类静态-子类静态-父类构造-子类构造
        /*Child child = new Child();
        System.out.println(child.sex);*/

        // 调用类的静态方法
//        Father.printStr();


        // 被动引用
        // 常量引用
//        System.out.println(Father.sex);

        // 数组引用
//        List<Father> fathers = new ArrayList<>();

        // 子对象引用父对象的静态字段不会导致子对象初始化
        // 执行顺序：父类静态-父类构造
//        Child.printStr();
    }
}


class Father{

    static String name = "father";

    static String code = "001";

    final static String sex = "male";

    static {
        System.out.println("father static has run");
    }

    Father() {
        System.out.println("father constructor has run");
    }

    static void printStr() {
        System.out.println("fater static method print");
    }

}

class Child extends Father {

    static String code = "111";

    static {
        System.out.println("child static has run");
    }

    Child() {
        System.out.println("child constructor has run");
    }
}