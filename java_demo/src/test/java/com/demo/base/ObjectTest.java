package com.demo.base;

import cn.hutool.core.thread.ThreadUtil;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author TMW
 * @since 2019/11/26 17:26
 */
public class ObjectTest {

    @Test
    public void test() {
        System.out.println(Objects.equals("123", null));
        System.out.println(Objects.equals(null, null));

    }

    @Test
    public void test02() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);// 0.1
        BigDecimal y = b.subtract(c);// 0.1
        System.out.println(x.equals(y));// true
        System.out.println(x.compareTo(y));

    }

    @Test
    public void test03() {
        String[] s = new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        s = list.toArray(new String[0]);//没有指定类型的话会报错
        System.out.println(Arrays.asList(s));
    }

    @Test
    public void test04() {
        Integer aa = null;
        System.out.println(Objects.equals(1, aa));
        System.out.println(Integer.valueOf(1) == null);
        System.out.println(Integer.valueOf(1) == aa);
        System.out.println(Integer.valueOf(null));
        System.out.println(1 == aa);
    }

    @Test
    public void test05() {
        ArrayBlockingQueue<String> list = new ArrayBlockingQueue<>(100);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            ThreadUtil.execute(() -> {
                try {
                    countDownLatch.await();
                    for (int y = 0; y < 100; y++) {
                        list.add(Thread.currentThread().getName() + "-----" + ThreadLocalRandom.current().nextInt());
                        // System.out.println(Thread.currentThread().getName() + "-----" + ThreadLocalRandom.current());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            countDownLatch.countDown();
        }


        ThreadUtil.sleep(1000);

        list.forEach(System.out::println);

    }
}
