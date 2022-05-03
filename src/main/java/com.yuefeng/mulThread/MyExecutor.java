package com.yuefeng.mulThread;

import java.util.concurrent.Callable;

public class MyExecutor<T> {

    public synchronized MyFuture<T> execute(Callable subTask) {


        final Object lock = new Object();
        final ExecutorThread thread = new ExecutorThread(subTask, lock);
        thread.run();

        MyFuture future = new MyFuture(){

        } ;

        return null;
    }
}
