package com.yuefeng.proxy.proxys;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SdkDynamicProxy implements InvocationHandler {

    private Object realObject;

    public SdkDynamicProxy(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("entering" + method.getName());
        if (!method.isAccessible()) method.setAccessible(true);
        Object result = method.invoke(realObject, args);
        System.out.println("leaving" + method.getName());
        return result;
    }
}
