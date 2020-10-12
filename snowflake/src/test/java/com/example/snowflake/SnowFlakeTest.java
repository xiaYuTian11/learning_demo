package com.example.snowflake;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SnowFlakeTest {

    public static void main(String[] args) throws InterruptedException {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(0L, 0L);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<Long> set = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(0);
        final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(12, 24, 60, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("snowflake-test-%d").build()));
        for (int i = 0; i < 10; i++) {
            ListenableFuture<Boolean> future = executorService.submit(() -> {
                try {
                    countDownLatch.await();
                    for (int i1 = 0; i1 < 24; i1++) {
                        set.add(snowFlakeUtil.nextId());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.incrementAndGet();
                return true;
            });

            Futures.addCallback(future, new FutureCallback<Object>() {
                @Override
                public void onSuccess(@Nullable Object result) {
                    System.out.println(set.size());
                    System.out.printf("%s :snowflake is valid： %b \r\n", Thread.currentThread().getName(), 10 * 24 == set.size());
                    System.out.println(new HashSet<>(set).size() == set.size());
                    if (count.get() == 10) {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        executorService.shutdown();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            }, executorService);
            countDownLatch.countDown();
        }
    }
}
