package com.tmw.thread.status;

/**
 * 守护线程。虚拟机不用等待守护线程执行完毕；
 *
 * @author TMW
 * @since 2020/3/20 21:44
 */
public class DaemonTest {
    public static void main(String[] args) {

        Runnable runnable = () -> {
            while (true) {
                System.out.println("上帝与你同在");
            }
        };

        Runnable runnable1 = () -> {
            for (int i = 0; i < 36500; i++) {
                System.out.println("you live day" + i);
            }
            System.out.println("you dead");
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        new Thread(runnable1).start();

    }
}
