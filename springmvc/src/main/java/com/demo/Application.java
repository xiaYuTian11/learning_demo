package com.demo;

import org.apache.catalina.startup.Tomcat;

/**
 * @author TMW
 * @since 2020/3/8 11:58
 */
public class Application {

    public void run() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9000);
        tomcat.addWebapp("/", "D:\\java");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
