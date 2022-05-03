package com.yuefeng.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 强软弱虚的四种引用
 * vm option: -Xms10M -Xmx10M -XX:+PrintGCDetails
 */
public class _09Reference {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        // 强引用：对象内存存放的绝大部分对象都是强引用，程序代码之中存在引用赋值，无论任何情况下，只要引用链还存在，就不会被gc
        // 软引用：类似缓存之类的对象引用，在内存空间不足的时候，会进行二次gc，此时被标记弱引用的对象会被gc
        /*SoftReference sr = new SoftReference<Object>(object);
        object = null;
        System.gc();
        System.out.println("空间足够，软引用被gc，结果为：" + sr.get());
        try{
            Byte[] bytes = new Byte[1024 * 1024 * 8];
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("空间不足，软引用被gc，结果为：" + sr.get());
        }*/

        // 弱引用：只能存活到下一次gc前，下一次gc该对象一定会被清除
        WeakReference wr = new WeakReference<Object>(object);
        object = null;
        System.out.println("gc前：" + wr.get());
        System.gc();
        Thread.sleep(1000000);
        System.out.println("gc后：" + wr.get());

        // 虚引用：本身存在只是为了在对象被回收的时候，发出通知回收情况，一定要结合引用队列使用
//        ReferenceQueue rq = new ReferenceQueue();
//        PhantomReference pr = new PhantomReference<Object>(object, rq);

    }


}
