package com.yuefeng.jvm;

public class _098byteVSByte {

    public static void main(String[] args) throws InterruptedException {
//        byte b = 127;
//        Byte bb = new Byte(b);
        // 未注释：6267 注释：5243 占用堆内存 = 6267 - 5243 = 1024
        byte[] b = new byte[1024*1024*2-1000*1024];
        Thread.sleep(10000000);
//        Byte[] bb = new Byte[1024*1024];
    }
}
