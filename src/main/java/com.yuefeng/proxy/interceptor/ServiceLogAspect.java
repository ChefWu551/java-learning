package com.yuefeng.proxy.interceptor;

import com.yuefeng.annotation.annotations.Aspect;
import com.yuefeng.proxy.service.ServiceA;
import com.yuefeng.proxy.service.impl.ServiceBImpl;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect({ServiceA.class, ServiceBImpl.class})
public class ServiceLogAspect {

    public static void before(Object object, Method method, Object[] args) {
        System.out.println("entering " + method.getDeclaringClass().getSimpleName() + "::" + method.getName() + ", args: " + Arrays.toString(args));
    }

    public static void after(Object object, Method method, Object[] args, Object result) {
        System.out.println("leaving " + method.getDeclaringClass().getSimpleName() + "::" + method.getName() + ", result: " + result);
    }

    public static void exception(Object object, Method method, Object[] args, Throwable e) {
        System.err.println("exception when calling: " + method.getName() + ", " + Arrays.toString(args));
    }

}
