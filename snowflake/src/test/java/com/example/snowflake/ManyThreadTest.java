package com.example.snowflake;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ManyThreadTest {

    public static void main(String[] args) throws InterruptedException {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(0L, 0L);
        List<Long> set = new ArrayList<>(300);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(12, 24, 60, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("snowflake-test-%d").build()));

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    countDownLatch.await();
                    for (int i1 = 0; i1 < 10; i1++) {
                        set.add(snowFlakeUtil.nextId());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            countDownLatch.countDown();
        }
        TimeUnit.SECONDS.sleep(20);
        System.out.println(set.size());
        System.out.printf("%s :snowflake is valid： %b \r\n", Thread.currentThread().getName(), 10 * 30 == set.size());
        System.out.println(new HashSet<>(set).size() == set.size());
        executorService.shutdown();
    }
}
