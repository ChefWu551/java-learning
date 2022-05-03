package com.yuefeng.proxy;

import com.yuefeng.proxy.aop.CGLibContainer;
import com.yuefeng.proxy.service.ServiceA;
import com.yuefeng.proxy.service.impl.ServiceAImpl;

public class AopMain {

    public static void main(String[] args) {
        ServiceA serviceA = CGLibContainer.getInstance(ServiceAImpl.class);
        serviceA.runServiceB();
    }

    // 线程安全的几种方式
    // 1. synchronized
    // 2. 锁机制
    // 3. 并发性容器 atomicInteger concurrentHashMap CopyOnWriteArrayList ConcurrentLinkedQueue ConcurrentSkipListSet
    // 4. 同步容器 synchronizedCollection SynchronizedList SynchronizedMap
}
