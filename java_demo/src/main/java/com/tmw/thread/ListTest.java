package com.tmw.thread;

import com.tmw.util.ThreadPoolUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author TMW
 * @since 2020/3/20 21:59
 */
public class ListTest {
    @Test
    public void test() throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ThreadPoolUtil.getThreadPool().execute(() -> list.add(Thread.currentThread().getName()));
        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }

    @Test
    public void test2() throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ThreadPoolUtil.getThreadPool().execute(() -> list.add(Thread.currentThread().getName()));
        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }
}
