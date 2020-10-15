package com.example.snowflake;

import cn.hutool.core.collection.ConcurrentHashSet;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author TMW
 * @date 2020/10/13 15:29
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, SnowFlakeBackExtension.class})
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

        for (int i = 0; i < size; i++) {
            if (i >= 3) {
                try {
                    Assertions.assertThat(extension.nextId()).isInstanceOf(RuntimeException.class);
                } catch (RuntimeException e) {
                    Assertions.assertThat(e.getMessage()).isEqualTo("Clock moved backwards.  Refusing to generate id");
                }
            } else {
                extension.nextId();
            }
        }

        PowerMockito.when(System.currentTimeMillis()).thenReturn(new Date().getTime());
        for (int i = 0; i < size; i++) {
            Assertions.assertThat(extension.nextId()).isInstanceOf(Long.class);
        }
    }
}