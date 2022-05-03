package com.yuefeng.proxy.service.impl;

import com.yuefeng.proxy.service.ServiceB;

public class ServiceBImpl implements ServiceB {
    @Override
    public void sayWorld() {
        System.out.println("serviceB run world");
    }
}
