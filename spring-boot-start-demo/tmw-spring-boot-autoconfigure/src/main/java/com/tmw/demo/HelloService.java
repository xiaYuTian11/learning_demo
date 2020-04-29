package com.tmw.demo;

import org.springframework.stereotype.Service;

/**
 * @author TMW
 * @since 2020/4/6 17:30
 */
@Service
public class HelloService {
    TmwProperties tmwProperties;

    public TmwProperties getTmwProperties() {
        return tmwProperties;
    }

    public void setTmwProperties(TmwProperties tmwProperties) {
        this.tmwProperties = tmwProperties;
    }

    public String helloStart(String name) {
        return tmwProperties.getPrefix() + name + tmwProperties.getSuffix();
    }
}
