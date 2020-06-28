package com.tmw.thread;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.*;

/**
 * @author TMW
 * @date 2020/6/24 10:28
 */
public class SettableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final SettableFuture<String> settableFuture = SettableFuture.create();

        // CountDownLatch countDownLatch = new CountDownLatch(1);

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(12, 24, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("tmw-$d").build()));
        ListenableFuture<String> zhangsan = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                settableFuture.set("zhangsan");
                return "zhangsan11";
            }
        });
        // countDownLatch.await();
        // System.out.println(zhangsan.get());
        System.out.println(settableFuture.get());

        executorService.shutdown();
    }
}
