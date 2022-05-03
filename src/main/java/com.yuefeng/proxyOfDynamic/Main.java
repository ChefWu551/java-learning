package com.yuefeng.proxyOfDynamic;

import com.yuefeng.proxyOfDynamic.model.Calculator;
import com.yuefeng.proxyOfDynamic.model.MyCalculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new MyCalculator();
        /**
         * 通过java sdk内置的代理对象，使用接口代理对实际代理对象进行切面操作
         * @method Proxy.newProxyInstance
         * @Param ClassLoader=>calculator.getClass().getClassLoader()
         * @Param Class<?>[] interface=>new Class[]{Calculator.class}
         * @Param InvocationHandler=>new MyInvocationHandler(方法调用的对象)
         */
        Calculator calculator1 = (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(), new Class[]{Calculator.class}, new MyInvocationHandler(calculator));
        System.out.println(calculator1.add(1, 1));
        System.out.println(calculator1.div(1, 0));
    }

    static class MyInvocationHandler implements InvocationHandler {
        Object object;

        MyInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                // aop=>@Before
                System.out.println("方法执行前，获取执行的方法名：" + method.getName() + " ; 方法参数值：" + Arrays.asList(args));
                // ★注意：这里的object是通过反射调用对应的实例对象的方法
                Object value = method.invoke(object, args);
                // aop=>@AfterReturning
                System.out.println("方法执行后，获取执行的结果：" + value);
                return value;
            } catch (Exception e) {
                // aop=>@AfterThrowing
                System.out.println("方法执行异常，默认返回0");
                return 0;
//                throw new RuntimeException("方法执行异常");
            } finally {
                // aop=>@After
                System.out.println("方法执行返回后");
            }

        }
    }
}
