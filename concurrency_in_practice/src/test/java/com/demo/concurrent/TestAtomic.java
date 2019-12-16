package com.demo.concurrent;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程安全性测试
 *
 * @author TMW
 * @since 2019/12/15 14:56
 */
public class TestAtomic {
    private Long count = 0L;
    private AtomicLong atomicCount = new AtomicLong(0);
    private volatile Long volatileCount = 0L;

    @Test
    public void test01() {
        TestAtomic testAtomic = new TestAtomic();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execute(testAtomic::setCount);
        }
        ThreadUtil.sleep(10000);
        // Thread.yield();
        System.out.println(testAtomic.getCount());
        System.out.println(testAtomic.getAtomicCount());
        System.out.println(testAtomic.getVolatileCount());
    }

    private Long getCount() {
        return count;
    }

    private Long getAtomicCount() {
        return atomicCount.get();
    }

    private Long getVolatileCount() {
        return volatileCount;
    }

    private void setCount() {
        ++count;
        atomicCount.incrementAndGet();
        ++volatileCount;
    }

    // private synchronized void setCount() {
    //     ++count;
    //     atomicCount.incrementAndGet();
    // }
}
