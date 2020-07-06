package com.tmw.guava.eventbus;

/**
 * @author TMW
 * @date 2020/7/6 14:31
 */
public class TextEventBus {
    private final String message;

    public TextEventBus(String message) {
        this.message = message;
        System.out.println("event message:"+message);
    }

    public String getMessage() {
        return this.message;
    }

}
