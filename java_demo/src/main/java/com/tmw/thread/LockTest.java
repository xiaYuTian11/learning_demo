package com.tmw.thread;

import com.tmw.util.ThreadPoolUtil;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author TMW
 * @since 2020/3/20 22:12
 */
public class LockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Runnable runnable = () -> {
            try {
                lock.lock();
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        ThreadPoolUtil.getThreadPool().execute(runnable);
    }
}
