package com.demo.msb.day01;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试
 *
 * @author TMW
 * @date 2019/12/19 10:16
 */
public class DeadlockTest {

    public synchronized void method01() {
        // ThreadUtil.sleep(10, TimeUnit.SECONDS);
        // for (int i = 0; i < 100; i++) {
        //     System.out.println("method01...");
        // }
        System.out.println("method01...");
    }

    public synchronized void method02() {
        // for (int i = 0; i < 100; i++) {
        //     System.out.println("method02...");
        // }

        System.out.println("method02...");
    }

    public synchronized void test01(){
        method01();
        method02();
    }

    public synchronized void test02(){
        method02();
        method01();

    }

    public static void main(String[] args) {
        DeadlockTest deadlockTest = new DeadlockTest();
        ThreadUtil.execute(() -> {
            // deadlockTest.method01();
            // deadlockTest.method02();
            deadlockTest.test01();
        });

        ThreadUtil.execute(() -> {
            // deadlockTest.method02();
            // deadlockTest.method01();
            deadlockTest.test02();
        });
        // ThreadUtil.execute(()->{
        //     deadlockTest.method01();
        //     deadlockTest.method02();
        //
        // });
        System.out.println("..............");
    }

}
