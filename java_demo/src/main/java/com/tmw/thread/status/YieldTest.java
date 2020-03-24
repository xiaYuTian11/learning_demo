package com.tmw.thread.status;

import com.tmw.util.ThreadPoolUtil;

/**
 * @author TMW
 * @since 2020/3/20 21:12
 */
public class YieldTest {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            Thread.yield();// 礼让，不一定成功
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            System.out.println(Thread.currentThread().getName() + "执行接收");
        };
        // for (int i = 0; i < 100; i++) {
        //     ThreadPoolUtil.getThreadPool("11").execute(runnable);
        // }
        // ThreadPoolUtil.getThreadPool("11").execute(runnable);
        // ThreadPoolUtil.getThreadPool("22").execute(runnable);
        ThreadPoolUtil.getThreadPool().execute(runnable);
        ThreadPoolUtil.getThreadPool().execute(runnable);
        ThreadPoolUtil.shutdown();
    }
}
