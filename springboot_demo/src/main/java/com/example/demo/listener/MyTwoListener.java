package com.example.demo.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author TMW
 * @date 2020/7/20 16:47
 */
public class MyTwoListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationStartingEvent){
            System.out.println("run MyTwoListener ...");
        }

    }
}
