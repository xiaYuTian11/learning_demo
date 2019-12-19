package com.demo.concurrent.threadpool;

import org.junit.jupiter.api.Test;

/**
 * 线程池
 *
 * @author TMW
 * @date 2019/12/18 17:45
 */
public class ThreadPoolTest {
    @Test
    public void test01(){
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }
}
