package com.tmw.autocode;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 案管资金授权码生成
 *
 * @author TMW
 * @since 2020/6/22 14:22
 */
public class CpmAutoCode {

    @Test
    public void test01() {
        String randomNumbers = RandomUtil.randomNumbers(6);
        System.out.println(randomNumbers);

        System.out.println(System.currentTimeMillis());

        int i = RandomUtil.getSecureRandom().nextInt();
        System.out.println(i);
    }

    public void getAutoCode(Set<String> set) {
        String numbers = RandomUtil.randomNumbers(6);
        // System.out.println(set.size() + 1 + ":" + numbers);
        System.out.println(Thread.currentThread().getName() + ":" + numbers);
        if (set.contains(numbers)) {
            getAutoCode(set);
        } else {
            set.add(numbers);
        }
    }

    @Test
    public void test02() {
        ReentrantLock reentrantLock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(40);

        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);

        ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<>();

        ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(5, 20);
        for (int i = 0; i < 40; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    countDownLatch.await();
                    for (int i1 = 0; i1 < 1000; i1++) {
                        getAutoCode(set);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            countDownLatch.countDown();
        }

        try {
            reentrantLock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }


        long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println(set.size());
        System.out.println("差值：" + (endTime - startTime));
    }

    @Test
    public void test03() {

        CountDownLatch countDownLatch = new CountDownLatch(400);
        long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);

        ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<>();

        ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(5, 20);
        for (int i = 0; i < 400; i++) {
            for (int i1 = 0; i1 < 1000; i1++) {
                getAutoCode(set);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println(set.size());
        System.out.println("差值：" + (endTime - startTime));
    }

}
