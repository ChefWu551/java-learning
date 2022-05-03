package com.yuefeng.proxy.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SimpleInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib entering: " + method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib leaving: " + method.getName());
        return result;
    }
}
