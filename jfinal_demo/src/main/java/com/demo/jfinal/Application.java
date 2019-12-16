package com.demo.jfinal;

import com.demo.jfinal.handler.Handler1;
import com.demo.jfinal.handler.Handler2;
import com.demo.jfinal.hello.HelloController;
import com.jfinal.config.*;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

/**
 * @author TMW
 * @version 1.0
 * @date 2019/9/10 21:14
 */
public class Application extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(Application.class);
    }

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        // constants.setRenderFactory();
        constants.setError404View("404.html");
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/hello", HelloController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {
        handlers.add(new Handler1());
        handlers.add(new Handler2());
    }
}
