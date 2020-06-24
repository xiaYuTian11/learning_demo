package com.tmw.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author TMW
 * @since 2020/6/22 17:28
 */
public class SemaphoreTest {
    /**
     * 默认非公平信号量
     * 公平信号量是指如果线程不在同步队列头部则排队等候；非公平信号量是指无论当前线程是否在同步队列头部，都会尝试获取信号量。
     */
    // private static final Semaphore SEMAPHORE = new Semaphore(3);
    // private static final Semaphore SEMAPHORE = new Semaphore(3, true);
    // 单例模式
    private static final Semaphore SEMAPHORE = new Semaphore(1);

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(12, 24, 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setThreadFactory(Thread::new).setNameFormat("thread-tmw-%d").build());

    private static class InformationThread extends Thread {
        private final String name;
        private final int age;

        public InformationThread(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public void run() {
            try {
                SEMAPHORE.acquire();
                System.out.println(Thread.currentThread().getName() + ":大家好，我是" + name + "我今年" + age + "岁当前时间为：" + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(name + "要准备释放许可证了，当前时间为：" + System.currentTimeMillis());
                System.out.println("当前可使用的许可数为：" + SEMAPHORE.availablePermits());
                SEMAPHORE.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test01() {
        String[] name = {"李明", "王五", "张杰", "王强", "赵二", "李四", "张三"};
        int[] age = {26, 27, 33, 45, 19, 23, 41};

        for (int i = 0; i < 7; i++) {
            THREAD_POOL_EXECUTOR.execute(new InformationThread(name[i], age[i]));
        }
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            THREAD_POOL_EXECUTOR.shutdown();
        }
    }

}
