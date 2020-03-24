package com.tmw.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author TMW
 * @since 2020/3/20 15:43
 */
public class ThreadPoolUtil {

    private static ThreadPoolExecutor ThreadPoolExecutor;

    /**
     * <p>
     * corepoolsize:在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，（
     * 除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程）。
     * 默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，
     * 就会把到达的任务放到缓存队列当中。核心线程在allowCoreThreadTimeout被设置为true时会超时退出，默认情况下不会退出。
     * </>
     * maxPoolSize:当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，直到线程数量达到maxPoolSize。如果线程数已等于maxPoolSize，且任务队列已满，则已超出线程池的处理能力，线程池会拒绝处理任务而抛出异常。
     * keepAliveTime:当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize。如果allowCoreThreadTimeout设置为true，则所有线程均会退出直到线程数量为0。
     * TimeUnit: 空闲线程的保留时间单位
     * BlockingQueue<Runnable>:阻塞队列，存储等待执行的任务。参数有ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue可选
     * ThreadFactory:线程工厂，用来创建线程
     * RejectedExecutionHandler:队列已满，而且任务量大于最大线程的异常处理策略。有以下取值:
     * <p>
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     *
     * @return
     */
    public static ThreadPoolExecutor getThreadPool() {
        if (ThreadPoolExecutor == null) {
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程 - %d").build();
            ThreadPoolExecutor = new ThreadPoolExecutor(5, 10, 0, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        }
        return ThreadPoolExecutor;
        // 使用完主要关闭线程池 shutdown();执行完任务在关闭  shutdownNow()；马上关闭
    }

    public static Executor getThreadPool(String threadName) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(threadName + "线程 - %d").build();
        return new ThreadPoolExecutor(5, 10, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        // 使用完主要关闭线程池 shutdown();执行完任务在关闭  shutdownNow()；马上关闭
    }

    public static void shutdown() {
        ThreadPoolExecutor.shutdown();
    }

    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
