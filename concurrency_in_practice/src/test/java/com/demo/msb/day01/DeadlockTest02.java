package com.demo.msb.day01;

import cn.hutool.core.thread.ThreadUtil;

/**
 * 死锁
 *
 * @author TMW
 * @date 2019/12/19 10:28
 */
public class DeadlockTest02 {

    private Object o1 = new Object();
    private Object o2 = new Object();

    public void leftRigth() {
        synchronized (o1) {
            ThreadUtil.sleep(1000);
            synchronized (o2) {
                System.out.println("leftRigth.....");
            }
        }
    }

    public void rigthLeft() {
        synchronized (o2) {
            ThreadUtil.sleep(1000);
            synchronized (o1) {
                System.out.println("rigthLeft.....");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockTest02 deadlockTest = new DeadlockTest02();
        ThreadUtil.execute(() -> {
            deadlockTest.leftRigth();
        });
        ThreadUtil.execute(() -> {
            deadlockTest.rigthLeft();
        });

        System.out.println("////////////////////////");
    }
}
