package com.yuefeng.mulThread.thread;

public class Counter {

    private int count = 0;

    public synchronized void incr() {
       count++;
    }

    public int getCount() {
        return count;
    }

}
