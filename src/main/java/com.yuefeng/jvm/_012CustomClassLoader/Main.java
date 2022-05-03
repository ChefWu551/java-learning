package com.yuefeng.jvm._012CustomClassLoader;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        File src = new File("D:\\myclasses\\src\\Car.class");
        File des = new File("D:\\myclasses\\des\\Car.class");
        EncryptUtil.encrypt(src, des);//加密
    }
}
