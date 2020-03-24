package com.tmw.thread.status;

import com.tmw.util.ThreadPoolUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TMW
 * @since 2020/3/20 20:53
 */
public class ThreadStop implements Runnable {
    private static boolean flag = true;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        while (flag) {
            System.out.println("执行线程。。。" + atomicInteger.get());
            atomicInteger.incrementAndGet();
        }
        System.out.println("线程停止。。。");
    }

    private static void stop() {
        flag = false;
        System.out.println("调用 stop() 方法。。。");
    }

    public static void main(String[] args) {
        Runnable runnable = new ThreadStop();
        // new Thread(runnable).start();
        ThreadPoolUtil.getThreadPool().execute(runnable);

        for (int i = 0; i < 10000; i++) {
            if (i == 900) {
                stop();
            }
            System.out.println(i);
        }
        System.out.println("执行完毕。。。");
        System.exit(1);
    }
}
