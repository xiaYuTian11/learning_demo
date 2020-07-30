package com.tmw.thread;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TMW
 * @date 2020/7/28 16:30
 */
public class ThreadPoolTest {
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    // Packing and unpacking ctl
    // 通过位运算获取线程池运行状态
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    // 通过位运算获取线程池中有效的工作线程数
    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    // 初始化ctl变量值
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    @Test
    public void test01() {
        System.out.println("COUNT_BITS:  " + COUNT_BITS);
        System.out.println("CAPACITY:    " + CAPACITY);
        System.out.println("RUNNING:     " + RUNNING);
        System.out.println("SHUTDOWN:    " + SHUTDOWN);
        System.out.println("STOP:        " + STOP);
        System.out.println("TIDYING:     " + TIDYING);
        System.out.println("TERMINATED:  " + TERMINATED);

        System.out.println("COUNT_BITS:  " + new BigInteger(String.valueOf(COUNT_BITS)).toString(2));
        System.out.println("CAPACITY:    " + new BigInteger(String.valueOf(CAPACITY)).toString(2));
        System.out.println("RUNNING:     " + new BigInteger(String.valueOf(RUNNING)).toString(2));
        System.out.println("SHUTDOWN:    " + new BigInteger(String.valueOf(SHUTDOWN)).toString(2));
        System.out.println("STOP:        " + new BigInteger(String.valueOf(STOP)).toString(2));
        System.out.println("TIDYING:     " + new BigInteger(String.valueOf(TIDYING)).toString(2));
        System.out.println("TERMINATED:  " + new BigInteger(String.valueOf(TERMINATED)).toString(2));
        System.out.println(2 << 29);

        System.out.println(new BigInteger(String.valueOf(ctl.get())).toString(2));
        System.out.println(new BigInteger(String.valueOf(runStateOf(ctl.get()))).toString(2));
    }

}
