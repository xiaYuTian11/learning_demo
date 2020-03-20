package com.tmw.net.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author TMW
 * @since 2020/3/20 14:56
 */
public class UdpSend {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        InputStreamReader reader = new InputStreamReader(System.in);
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String readLine = bufferedReader.readLine();
            if ("exit".equals(readLine)) {
                break;
            }
            byte[] lineBytes = readLine.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(lineBytes, 0, lineBytes.length, InetAddress.getByName("127.0.0.1"), 6666);
            datagramSocket.send(datagramPacket);
        }
        datagramSocket.close();
    }
}
