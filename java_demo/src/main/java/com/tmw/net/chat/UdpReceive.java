package com.tmw.net.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author TMW
 * @since 2020/3/20 15:01
 */
public class UdpReceive {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(6666);
        while (true) {
            byte[] bytes = new byte[1024];

            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
            datagramSocket.receive(datagramPacket);
            String str = new String(bytes, 0, datagramPacket.getLength());
            System.out.println(new String(bytes, 0, datagramPacket.getLength()));
            if ("exit".equals(str)) {
                break;
            }
        }

        datagramSocket.close();
    }
}
