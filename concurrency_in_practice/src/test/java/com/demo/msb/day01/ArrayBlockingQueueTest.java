package com.demo.msb.day01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author TMW
 * @date 2019/12/19 17:05
 */
public class ArrayBlockingQueueTest {

    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            blockingQueue.add(i);
        }

        // blockingQueue.add(1);
        // blockingQueue.put(2);
        // blockingQueue.offer(1);
        blockingQueue.offer(1, 1000, TimeUnit.MILLISECONDS);
        System.out.println(blockingQueue);
    }

}
