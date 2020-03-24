package com.tmw.thread;

import java.util.concurrent.*;

/**
 * @author TMW
 * @since 2020/3/20 17:45
 */
public class CallableTest implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "执行了......");
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Boolean> callable1 = new CallableTest();
        Callable<Boolean> callable2 = new CallableTest();
        Callable<Boolean> callable3 = new CallableTest();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Boolean> submit1 = executorService.submit(callable1);
        Future<Boolean> submit2 = executorService.submit(callable2);
        Future<Boolean> submit3 = executorService.submit(callable3);
        System.out.println(submit1.get());
        System.out.println(submit2.get());
        System.out.println(submit3.get());
        Runnable runnable = () -> System.out.println("heloo");
        runnable.run();
    }
}
