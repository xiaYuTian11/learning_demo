package com.demo.msb.day01;

/**
 * @author TMW
 * @date 2019/12/19 9:55
 */
public class SyncTest {

    private int count = 0;
    private Object o = new Object();

    public void setCount() {
        synchronized (o) {
            count--;
            System.out.println("---------------------");
        }
    }

}
