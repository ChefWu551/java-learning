package com.yuefeng.mulThread.thread;

public class ThreadCount extends Thread {

    Counter counter;

    public ThreadCount(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i=0; i<1000; i++) {
            counter.incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread[] threads = new Thread[100];
        for (int i=0; i<threads.length; i++) {
            threads[i] = new ThreadCount(counter);
            threads[i].start();
        }

        for (int i=0; i<threads.length; i++) {
            threads[i].join();
        }

        System.out.println(counter.getCount());
    }
}
