package com.tmw.socket;

import cn.hutool.socket.SocketConfig;
import cn.hutool.socket.SocketUtil;
import cn.hutool.socket.nio.NioClient;

/**
 * @author TMW
 * @date 2020/6/23 14:42
 */
public class HutoolSocketClientDemo {

    public static void main(String[] args) {
        NioClient nioClient = new NioClient("127.0.0.1", 9999);

        nioClient.read(null);

    }

}
