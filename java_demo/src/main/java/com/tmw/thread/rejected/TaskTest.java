package com.tmw.thread.rejected;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author TMW
 * @date 2020/10/19 14:22
 */
public class TaskTest {

    public static void main(String[] args) {
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        RejectedTaskHandler handler=new RejectedTaskHandler();

        executor.setRejectedExecutionHandler(handler);

        for (int i=0; i<24; i++)
        {
            Task task=new Task("Task-"+i);
            executor.execute(task);
        }

        //shut down the executor so that new tasks will be rejected
        executor.shutdown();

        Task task = new Task("Rejected task");
        executor.execute(task);
    }

}
