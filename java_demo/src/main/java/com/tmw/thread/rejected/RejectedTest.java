package com.tmw.thread.rejected;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author TMW
 * @date 2020/10/19 14:42
 */
public class RejectedTest {

    private static ThreadPoolExecutor executor;

    @Test
    public void test01() {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("rejected-test-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " --- do something");
            });
        }
    }

    @Test
    public void test02() {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("rejected-test-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 20; i++) {
            executor.execute(() -> System.out.println(Thread.currentThread().getName() + " --- do something"));
        }
    }

    @Test
    public void test03() {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                60, TimeUnit.SECONDS, new PriorityBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("rejected-test-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 20; i++) {
            executor.execute(() -> System.out.println(Thread.currentThread().getName() + " --- do something"));
        }
    }

    @Test
    public void test04() {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                60, TimeUnit.SECONDS, new SynchronousQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("rejected-test-pool-%d").build(),
                // new ThreadPoolExecutor.AbortPolicy());
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 20; i++) {
            executor.execute(() -> System.out.println(Thread.currentThread().getName() + " --- do something"));
        }
    }


    @AfterAll
    public static void destroy(){
        if(executor != null){
            executor.shutdown();
        }
    }
}
