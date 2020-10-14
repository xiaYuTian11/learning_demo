package com.example.snowflake;

import org.junit.jupiter.api.Test;

/**
 * @author TMW
 * @date 2020/10/13 11:28
 */
public class SnowTest {
    @Test
    public void test01(){
        SnowFlakeUtil util = new SnowFlakeUtil(2,3);
        final long l = util.nextId();
        final String valueOf = String.valueOf(l);
        System.out.println(valueOf);
        System.out.println(valueOf.length());
    }
}
