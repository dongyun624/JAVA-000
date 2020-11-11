package com.pratise.ThreadTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : dabing
 * @date : 2020/11/11
 */
public class ThreadMain1 {

    private static Integer result = null;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(1);


        executorService.execute(new FiboTask(countDownLatch));
        countDownLatch.await();
        System.out.println("result : " + result);

        executorService.shutdown();//关闭线程池
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
        }

    }


    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    static class FiboTask implements Runnable {
        CountDownLatch countDownLatch;

        public FiboTask(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            try {
                result = fibo(45);
            } finally {
                countDownLatch.countDown();
            }

        }
    }

}
