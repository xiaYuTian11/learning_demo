package com.tmw.thread;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可见度问题 volatile  synchronized
 *
 * @author TMW
 * @date 2020/6/24 9:50
 */
public class VisibilityIssue {

    private static final int TOTAL = 200000;
    private AtomicInteger count;

    public static void main(String[] args) throws InterruptedException {
        // ListeningExecutorService executorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(12, 24, 10, TimeUnit.SECONDS,
        //         new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setThreadFactory(Thread::new).setNameFormat("tmw-%d").build()));
        VisibilityIssue visibilityIssue = new VisibilityIssue();
        Thread thread1 = new Thread(() -> {
            visibilityIssue.addCount();
        });
        Thread thread2 = new Thread(() -> {
            visibilityIssue.addCount();
        });

        CountDownLatch countDownLatch = new CountDownLatch(2);
        // for (int i = 0; i < 2; i++) {
        //     executorService.execute(() -> {
        //         countDownLatch.countDown();
        //         visibilityIssue.addCount();
        //     });
        // }

        thread1.start();
        thread2.start();
        // countDownLatch.await();


        thread1.join();
        thread2.join();
        System.out.println("-------------------");
        System.out.println(visibilityIssue.count);
    }

    private void addCount() {
        int startCount = 0;
        if (count == null) {
            count = new AtomicInteger(0);
        }
        while (startCount++ < TOTAL) {
            System.out.println(count.incrementAndGet());
            // System.out.println(this.count);
        }
    }
}
