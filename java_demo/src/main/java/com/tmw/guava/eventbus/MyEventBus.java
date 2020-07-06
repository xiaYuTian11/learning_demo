package com.tmw.guava.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.Test;

/**
 * https://www.cnblogs.com/peida/p/EventBus.html
 *
 * @author TMW
 * @date 2020/7/6 14:29
 */
public class MyEventBus {
    @Test
    public void test() {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TextEventBus("200"));
        eventBus.post(new TextEventBus("300"));
        eventBus.post(new TextEventBus("400"));

        System.out.println("LastMessage:" + listener.getLastMessage());
        ;
    }
}
