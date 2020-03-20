package com.tmw.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取IP地址
 *
 * @author TMW
 * @since 2020/3/20 10:04
 */
public class IntAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
        System.out.println(address);
        for (InetAddress inetAddress : allByName) {
            System.out.println(inetAddress);
        }
    }
}
