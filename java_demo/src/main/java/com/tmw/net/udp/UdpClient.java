package com.tmw.net.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author TMW
 * @since 2020/3/20 14:32
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        String str = "你好，服务器";
        DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(), 0, str.getBytes().length, InetAddress.getByName("127.0.0.1"), 9898);
        datagramSocket.send(datagramPacket);

        System.out.println("send...");
        datagramSocket.close();
    }
}
