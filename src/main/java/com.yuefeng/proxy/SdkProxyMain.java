package com.yuefeng.proxy;

import com.yuefeng.annotation.annotations.SimpleInject;
import com.yuefeng.proxy.proxys.SdkDynamicProxy;
import com.yuefeng.proxy.service.ServiceA;
import com.yuefeng.proxy.service.ServiceB;
import com.yuefeng.proxy.service.impl.ServiceAImpl;
import com.yuefeng.proxy.service.impl.ServiceBImpl;

import java.lang.reflect.Proxy;

// todo: 增强功能，直接用注解初始化实例，引入代理对象中使用
public class SdkProxyMain {

    public static void main(String[] args) {
        SdkProxyMain sdkProxyMain = new SdkProxyMain();
        sdkProxyMain.run();
    }

    private void run() {
        ServiceA realServiceA = new ServiceAImpl();
        ServiceA a = getProxy(ServiceA.class, realServiceA);
//        Proxy.newProxyInstance(ServiceA.class.getClassLoader(), new Class<?>[]{ServiceA.class}, new SdkDynamicProxy(realServiceA));
        a.sayHello();

        ServiceB realServiceB = new ServiceBImpl();
        ServiceB b = getProxy(ServiceB.class, realServiceB);
        b.sayWorld();
    }

    @SuppressWarnings("unchecked")
    private static <T> T getProxy(Class<T> iClass, Object realObject) {
        return (T)Proxy.newProxyInstance(iClass.getClassLoader(), new Class<?>[]{iClass}, new SdkDynamicProxy(realObject));
    }
}
