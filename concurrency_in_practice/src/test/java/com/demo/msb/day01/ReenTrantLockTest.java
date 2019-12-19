package com.demo.msb.day01;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author TMW
 * @date 2019/12/19 14:58
 */
public class ReenTrantLockTest {
    Lock lock = new ReentrantLock();

    public void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                ThreadUtil.sleep(1000);
                System.out.println(i);
            }
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        lock.lock();
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        System.out.println("m2....");
        lock.unlock();
    }

    @Test
    public void test01() {
        ReenTrantLockTest reenTrantLockTest = new ReenTrantLockTest();
        new Thread(reenTrantLockTest::m1).start();

        ThreadUtil.sleep(1000);

        new Thread(reenTrantLockTest::m2).start();
    }

    public static void main(String[] args) {
        ReenTrantLockTest reenTrantLockTest = new ReenTrantLockTest();
        new Thread(reenTrantLockTest::m1).start();

        ThreadUtil.sleep(1000);

        new Thread(reenTrantLockTest::m2).start();
    }
}
