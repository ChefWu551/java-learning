package com.yuefeng.mulThread;

import java.util.Random;
import java.util.concurrent.*;

public class AsynClassTest {

    static class SubTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int timeSleep = new Random().nextInt(1000);
            Thread.sleep(timeSleep);
            System.out.println("sub Thread sleep" + timeSleep);
            return timeSleep;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> f = executor.submit(new SubTask());
        Thread.sleep(100);
        try {
            System.out.println(f.get());
            System.out.println("main thread has been run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

}
