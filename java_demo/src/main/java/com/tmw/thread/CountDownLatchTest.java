package com.tmw.thread;

import cn.hutool.core.thread.ThreadUtil;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程计数器
 *
 * @author TMW
 * @since 2020/6/22 16:02
 */
public class CountDownLatchTest {

    @Test
    public void test01() {
        CountDownLatch countDownLatch = new CountDownLatch(20);

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(12, 24, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setThreadFactory(Thread::new).setNameFormat("tmw-%d-tmw").build()));
        for (int i = 0; i < 20; i++) {
            AtomicInteger atomicInteger = new AtomicInteger(i);
            listeningExecutorService.execute(() -> {
                System.out.println("张三" + atomicInteger.get() + "来上班");
                countDownLatch.countDown();
                System.out.println("门卫大爷给" + "张三" + atomicInteger.get() + "测了体温");
            });
        }
        try {
            System.out.println("门卫大爷等待员工上班中...");
            countDownLatch.await();
            System.out.println("门卫大爷测完体温去休闲了...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            listeningExecutorService.shutdown();
        }
    }

    @Test
    public void test06() {
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {            e.printStackTrace();        }
    }

    @Test
    public void test04() {
        CountDownLatch countDownLatch = new CountDownLatch(20);

        ThreadPoolExecutor listeningExecutorService = ThreadUtil.newExecutor(12, 24);
        for (int i = 0; i < 20; i++) {
            AtomicInteger atomicInteger = new AtomicInteger(i);
            listeningExecutorService.execute(() -> {
                System.out.println("张三" + atomicInteger.get() + "来上班");
                countDownLatch.countDown();
                System.out.println("门卫大爷给" + "张三" + atomicInteger.get() + "测了体温");
            });
        }
        try {
            System.out.println("门卫大爷等待员工上班中...");
            countDownLatch.await();
            System.out.println("门卫大爷测完体温去休闲了...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            listeningExecutorService.shutdown();
        }
    }

    @Test
    public void test02() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(12, () -> System.out.println("begin"));
        // ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(10, 24, 60, TimeUnit.SECONDS,
        //         new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setThreadFactory(Thread::new).setNameFormat("thread-%d:").build()));
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(10, 24, 60, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactoryBuilder().setThreadFactory(Thread::new).setNameFormat("thread-%d:").build()));
        for (int i = 0; i < 12; i++) {
            listeningExecutorService.execute(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在准备");
                    Thread.sleep(1000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "准备好了");
                    cyclicBarrier.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            listeningExecutorService.shutdown();
        }
    }

    @Test
    public void test03() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(12, () -> System.out.println("选手准备就绪,准备开始"));
        ThreadPoolExecutor listeningExecutorService = ThreadUtil.newExecutor(10, 24);
        for (int i = 0; i < 12; i++) {
            listeningExecutorService.execute(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在准备");
                    Thread.sleep(1000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "准备好了");
                    cyclicBarrier.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            listeningExecutorService.shutdown();
        }
    }

    @Test
    public void test05() {
        Semaphore semaphore = new Semaphore(8);

    }

}
