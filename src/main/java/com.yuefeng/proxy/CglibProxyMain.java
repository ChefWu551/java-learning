package com.yuefeng.proxy;

import com.yuefeng.proxy.interceptor.SimpleInterceptor;
import com.yuefeng.proxy.service.ServiceA;
import com.yuefeng.proxy.service.impl.ServiceAImpl;
import net.sf.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.List;

public class CglibProxyMain {

    public static void main(String[] args) {
//        for(int i=0; i< 100; i++){
        List list = new ArrayList();

        while (true) {
            ServiceA serviceA = getProxy(ServiceAImpl.class);
            list.add(serviceA);
        }

//        }

//        serviceA.sayHello();
    }

    private static <T> T getProxy(Class cls) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new SimpleInterceptor());
        return (T) enhancer.create();
    }


}
