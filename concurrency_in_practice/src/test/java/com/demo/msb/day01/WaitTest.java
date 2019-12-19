package com.demo.msb.day01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TMW
 * @date 2019/12/19 11:28
 */
public class WaitTest {
    private List<String> list = new ArrayList<>();

    public void add(String str) {
        list.add(str);
    }

    public Integer getSize() {
        return list.size();
    }

    public static void main(String[] args) {
        WaitTest wt = new WaitTest();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                if (wt.getSize() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程2结束。。。");
                lock.notify();
            }

        }, "t2").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    wt.add(Integer.toString(i));
                    System.out.println("add " + i);
                    if (wt.getSize() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("线程1结束");
            }
        }, "t1").start();

    }

}
