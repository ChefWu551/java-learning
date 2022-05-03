package com.yuefeng.jvm;



public class _12ClassLoader {

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统默认类加载器： " + systemClassLoader);
        String fatherName = systemClassLoader.getClass().getSuperclass().getName();
        System.out.println("系统默认类加载器的父类： " + fatherName);

        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("appClassLoader的父类加载器： " + extClassLoader);

        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("extClassLoader的父类加载器： " + bootstrapClassLoader);

        // bootstrapClassLoader父类为null
        ClassLoader strCL= String.class.getClassLoader();
        System.out.println("string类加载器： " + strCL + "； 使用的是bootstrap类加载器");

        // 基本类型数组 - null，说明基本类型是不需要加载的，虚拟机预先定义好了
        // string类型数组 - null，bootstrap类加载器
        // 以及自定义数组的classLoader类型 - appClassLoader
//        Launcher

    }
}
