package com.example.demo.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author TMW
 * @date 2020/7/20 16:47
 */
public class MyFirstListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationStartedEvent){
            System.out.println("run MyFirstListener ...");
        }
    }
}
