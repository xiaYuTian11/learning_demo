package com.demo;

import org.apache.catalina.startup.Tomcat;

/**
 * @author TMW
 * @since 2020/3/8 13:18
 */
public class Application {

    public static   void run() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);
        tomcat.addWebapp("/", "D:\\java\\");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("tomcat init......");

    }
}
