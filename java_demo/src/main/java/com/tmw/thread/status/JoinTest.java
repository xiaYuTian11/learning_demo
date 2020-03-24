package com.tmw.thread.status;

/**
 * 强制加入线程
 *
 * @author TMW
 * @since 2020/3/20 21:28
 */
public class JoinTest {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("runnable" + i);
            }
        };

        Thread thread = new Thread(runnable);

        for (int i = 0; i < 100000; i++) {
            if (i == 100) {
                try {
                    thread.start();
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main" + i);
        }
    }
}
