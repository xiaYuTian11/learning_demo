package com.demo.concurrent;

/**
 * 发布与逸出
 *
 * @author TMW
 * @date 2019/12/18 10:29
 */
public class PublishTest {

    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates() {
        return states;
    }

}
