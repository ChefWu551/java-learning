package com.yuefeng.annotation;

import com.yuefeng.annotation.annotations.SimpleInject;
import com.yuefeng.annotation.service.ServiceA;
import com.yuefeng.annotation.service.ServiceB;

import java.lang.reflect.Field;

public class ServiceMainClass {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        ServiceA serviceA = getInstance(ServiceA.class);
        serviceA.callB();
    }

    public static <T> T getInstance(Class<T> t) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        T service =  t.newInstance();
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(SimpleInject.class)) {
                if (!field.isAccessible()) field.setAccessible(true);
                Class<?> fieldClass = field.getType();
                field.set(service, getInstance(fieldClass));
            }
        }

        return service;
    }
}
