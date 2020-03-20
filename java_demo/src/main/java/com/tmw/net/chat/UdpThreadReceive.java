package com.tmw.net.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author TMW
 * @since 2020/3/20 15:24
 */
public class UdpThreadReceive implements Runnable {
    DatagramSocket datagramSocket = null;
    private int ownPort;

    public UdpThreadReceive(int ownPort) {
        this.ownPort = ownPort;
        try {
            datagramSocket = new DatagramSocket(ownPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        datagramSocket.close();
    }
}
