package com.yuefeng.proxy.service.impl;

import com.yuefeng.annotation.annotations.SimpleInject;
import com.yuefeng.proxy.service.ServiceA;

public class ServiceAImpl implements ServiceA {

    @SimpleInject
    ServiceBImpl serviceBImpl;

    @Override
    public void sayHello() {
        System.out.println("serviceA run hello");
    }

    public void runServiceB() {
        System.out.println("serviceA run A");
        serviceBImpl.sayWorld();
    }
}
