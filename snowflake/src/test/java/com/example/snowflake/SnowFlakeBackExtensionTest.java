package com.example.snowflake;

import cn.hutool.core.collection.ConcurrentHashSet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author TMW
 * @date 2020/10/13 15:29
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, SnowFlakeBackExtension.class, SnowFlakeBackExtensionTest.class})
public class SnowFlakeBackExtensionTest {

    @Test
    public void nextId() {
        SnowFlakeBackExtension extension = new SnowFlakeBackExtension(1, 2);
        int size = 100;
        long beginTime = System.currentTimeMillis();
        Set<Long> set = new ConcurrentHashSet<>();
        for (int i = 0; i < size; i++) {
            set.add(extension.nextId());
        }
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.currentTimeMillis()).thenReturn(beginTime);
        Assert.assertEquals(System.currentTimeMillis(), beginTime);

        final long l = extension.nextId();
        PowerMockito.when(System.currentTimeMillis()).thenReturn(beginTime);
        final long l2 = extension.nextId();
        PowerMockito.when(System.currentTimeMillis()).thenReturn(beginTime);
        final long l3 = extension.nextId();
        PowerMockito.when(System.currentTimeMillis()).thenReturn(beginTime);
        final long l4 = extension.nextId();
        PowerMockito.when(System.currentTimeMillis()).thenReturn(beginTime);
        final long l5 = extension.nextId();
        long temp = (beginTime - 1577808000000L) << 22 | 1 << 17 | 2 << 12 | 0L << 2 | 1;

        set.forEach(System.out::println);
        System.out.println(l);
        System.out.println(temp);
    }
}