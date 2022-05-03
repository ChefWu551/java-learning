package com.yuefeng.annotation.service;

import com.yuefeng.annotation.annotations.SimpleInject;

public class ServiceA {

    @SimpleInject
    ServiceB serviceB;

    public void callB() {
        System.out.println("service A print");
        serviceB.action();
    }

}
