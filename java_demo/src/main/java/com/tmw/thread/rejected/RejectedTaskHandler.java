package com.tmw.thread.rejected;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author TMW
 * @date 2020/10/19 14:21
 */
public class RejectedTaskHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectedTaskHandler: The task %s has been rejected", r.toString());
    }
}
