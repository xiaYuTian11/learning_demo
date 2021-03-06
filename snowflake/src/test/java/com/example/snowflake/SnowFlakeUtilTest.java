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
        CountDownLatch countDownLatch = new CountDownLatch(100);
        List<Long> set = new ArrayList<>(10000000);
        AtomicInteger count = new AtomicInteger(0);
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                // try {
                // countDownLatch.await();
                for (int i1 = 0; i1 < 10; i1++) {
                    set.add(snowFlakeUtil.nextId());
                }
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
            });
            // countDownLatch.countDown();
        }
        while (set.size() != 100 * 10) {
            // System.out.println("... await ...");
        }
        // TimeUnit.SECONDS.sleep(20);
        System.out.println(count.get());
        System.out.println(set.size());
        System.out.printf("snowflake is valid： %b", 100 * 10 == set.size());
        final long elapsed = stopwatch.stop().elapsed(TimeUnit.SECONDS);
        System.out.println("\r\n耗时：" + elapsed);
        System.out.println(new HashSet<>(set).size() == set.size());
        threadPool.shutdown();
    }
}
