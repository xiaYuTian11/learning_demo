package com.demo.concurrent;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 发布与逸出
 *
 * @author TMW
 * @date 2019/12/18 10:30
 */
public class PublishTest2 {

    @Test
    public void test01(){
        PublishTest publishTest = new PublishTest();
        String[] states = publishTest.getStates();
        states = new String[]{"HH","HH"};

        String[] states1 = publishTest.getStates();
        System.out.println(Arrays.asList(states1));
    }

}
