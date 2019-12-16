package com.demo.jfinal.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author TMW
 * @version 1.0
 * @date 2019/9/17 21:13
 */
public class Handler2 extends Handler {
    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        System.out.println("执行 handler2");
        next.handle(target, request, response, isHandled);
    }
}
