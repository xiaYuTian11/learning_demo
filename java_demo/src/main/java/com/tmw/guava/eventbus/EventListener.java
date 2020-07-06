package com.tmw.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author TMW
 * @date 2020/7/6 14:33
 */
public class EventListener {
    public String lastMessage = "事件总线";

    @Subscribe
    public void listen(TextEventBus textEventBus) {
        lastMessage = textEventBus.getMessage();
        System.out.println("event lastMessage:" + lastMessage);
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
