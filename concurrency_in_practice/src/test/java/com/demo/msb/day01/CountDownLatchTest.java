package com.demo.msb.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 门栓
 * @author TMW
 * @date 2019/12/19 11:28
 */
public class CountDownLatchTest {
    private List<String> list = new ArrayList<>();

    public void add(String str) {
        list.add(str);
    }

    public Integer getSize() {
        return list.size();
    }

    public static void main(String[] args) {
        CountDownLatchTest wt = new CountDownLatchTest();
        CountDownLatch downLatch = new CountDownLatch(1);
        final Object lock = new Object();
        new Thread(() -> {
            if (wt.getSize() != 5) {
                try {
                    downLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程2结束。。。");
        }, "t2").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                wt.add(Integer.toString(i));
                System.out.println("add " + i);
                if (wt.getSize() == 5) {
                    downLatch.countDown();

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程1结束");
        }, "t1").start();

    }

}
