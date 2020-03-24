package com.tmw.thread.status;

/**
 * @author TMW
 * @since 2020/3/20 21:35
 */
public class StateTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
            }
        });
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        while (thread.getState() != Thread.State.TERMINATED) {
            System.out.println(thread.getState());
        }
        System.out.println(thread.getState());
    }
}
