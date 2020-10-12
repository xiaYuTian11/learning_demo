package com.example.snowflake;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SnowFlakeUtilTest {

    public static void main(String[] args) throws InterruptedException {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(0L, 0L);
        final ExecutorService threadPool = Executors.newFixedThreadPool(12);
        CountDownLatch countDownLatch = new CountDownLatch(12);
        List<Long> set = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(0);
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 12; i++) {
            threadPool.execute(() -> {
                try {
                    countDownLatch.await();
                    for (int i1 = 0; i1 < 24; i1++) {
                        set.add(snowFlakeUtil.nextId());
                    }
                    count.incrementAndGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            countDownLatch.countDown();
        }
        while (count.get() < 12) {
            System.out.println("... await ...");
        }
        System.out.println(count.get());
        System.out.println(set.size());
        System.out.printf("snowflake is valid： %b", 12 * 24 == set.size());
        stopwatch.stop();
        System.out.println("\r\n耗时：" + stopwatch.elapsed(TimeUnit.SECONDS));
        System.out.println(new HashSet<>(set).size() == set.size());
        threadPool.shutdown();
    }
}
