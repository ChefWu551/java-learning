package com.yuefeng.mulThread;

import java.util.concurrent.Callable;

public class MainClass {

    public static void main(String[] args) {
        // 1. 获取executor对象
        MyExecutor executor = new MyExecutor();

        @SuppressWarnings("unchecked")
        Callable<Integer> subTask = new Callable() {
            @Override
            public Integer call() throws Exception {
                int millis = (int)(Math.random() * 1000);
                Thread.sleep(1000);
                return millis;
            }
        };

        // 2. 执行executor方法返回future对象
        MyFuture<Integer> future = executor.execute(subTask);

        // 3. 处理future对象结果
        Integer value = future.getFuture();

        System.out.println("结果是：" + value);
    }
}
