package com.pratise.ThreadTask;

import java.util.concurrent.*;

/**
 * @author : dabing
 * @date : 2020/11/11
 */
public class ThreadMain2 {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future = executorService.submit(() -> {
           return fibo(45);
        });

        Integer result = future.get();
        System.out.println("result:" + result);

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

}
