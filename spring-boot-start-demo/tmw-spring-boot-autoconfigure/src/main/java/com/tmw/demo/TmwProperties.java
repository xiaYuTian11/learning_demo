package com.tmw.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author TMW
 * @since 2020/4/6 17:31
 */
@ConfigurationProperties(prefix = "tmw")
public class TmwProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
