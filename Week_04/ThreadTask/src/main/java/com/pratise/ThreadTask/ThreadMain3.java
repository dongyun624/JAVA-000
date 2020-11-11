package com.pratise.ThreadTask;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : dabing
 * @date : 2020/11/11
 */
public class ThreadMain3 {

    private static int result = 0;

    public static void main(String[] args) {
        int N = 1;
        CyclicBarrier barrier  = new CyclicBarrier(N,new Runnable() {
            @Override
            public void run() {
                System.out.println("result:" + result);
            }
        });

        for(int i=0;i<N;i++) {
            new Task(barrier).start();
        }

    }

    static class Task extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                result = fibo(45);
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

}
