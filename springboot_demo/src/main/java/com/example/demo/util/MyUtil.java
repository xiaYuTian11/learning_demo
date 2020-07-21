package com.example.demo.util;

import com.example.demo.properties.TmwProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author TMW
 * @date 2020/7/21 10:20
 */
@Component
public class MyUtil {

    private static TmwProperties tmwProperties;

    // @Autowired
    // public void getTmwProperties(TmwProperties tmwProperties) {
    //     MyUtil.tmwProperties = tmwProperties;
    // }

    @Autowired
    private TmwProperties tmwProperties1;
    @PostConstruct
    public void  init(){
        MyUtil.tmwProperties = tmwProperties1;
    }

    public static String getTmw() {
        return tmwProperties.toString();
    }
}
