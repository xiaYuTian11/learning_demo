package com.tmw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Zhengxin
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyProperties {

    private int tcpPort = 8080;

    private int bossCount = 4;

    private int workerCount = 16;

    private boolean keepAlive = true;

    private int backlog;

}
