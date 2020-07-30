package com.tmw.thread;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author TMW
 * @date 2020/7/28 15:08
 */
public class ThreadPoolPerformance {
    /**
     * 最大执行次数
     */
    public static final int MAX_COUNT = 100000;

    public static void main(String[] args) throws InterruptedException {
        // 线程测试代码
        ThreadPerformanceTest();

        // 线程池测试代码
        ThreadPoolPerformanceTest();
    }

    /**
     * 线程池性能测试
     */
    private static void ThreadPoolPerformanceTest() throws InterruptedException {
        // 开始时间
        long stime = System.currentTimeMillis();
        // 业务代码
        ThreadPoolExecutor tp = new ThreadPoolExecutor(8, 16, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setThreadFactory(Thread::new).build());
        for (int i = 0; i < MAX_COUNT; i++) {
            tp.execute(new PerformanceRunnable());
        }
        tp.shutdown();
        tp.awaitTermination(1, TimeUnit.SECONDS);  // 等待线程池执行完成
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("线程池执行时长：%d 毫秒.", (etime - stime));
        System.out.println();
    }

    /**
     * 线程性能测试
     */
    private static void ThreadPerformanceTest() {
        // 开始时间
        Stopwatch started = Stopwatch.createStarted();
        // 执行业务代码
        for (int i = 0; i < MAX_COUNT; i++) {
            Thread td = new Thread(new PerformanceRunnable());
            td.start();
            try {
                td.join(); // 确保线程执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 结束时间
        started.stop();
        // 计算执行时间
        System.out.printf("线程执行时长：%d 毫秒.", (started.elapsed(TimeUnit.SECONDS)));
        System.out.println();
    }

    /**
     * 业务执行类
     */
    static class PerformanceRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < MAX_COUNT; i++) {
                long num = i * i + i;
                // System.out.println(num);
            }
        }
    }

}
