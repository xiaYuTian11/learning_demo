package com.tmw.net.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author TMW
 * @since 2020/3/20 15:15
 */
public class UdpThreadSend implements Runnable {
    DatagramSocket datagramSocket = null;
    BufferedReader bufferedReader = null;
    private int ownPort;
    private String toIp;
    private int toPort;

    public UdpThreadSend(int ownPort, String toIp, int toPort) {
        this.ownPort = ownPort;
        this.toIp = toIp;
        this.toPort = toPort;
        try {
            datagramSocket = new DatagramSocket(ownPort);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String readLine = bufferedReader.readLine();
                if ("exit".equals(readLine)) {
                    break;
                }
                byte[] lineBytes = readLine.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(lineBytes, 0, lineBytes.length, InetAddress.getByName(toIp), toPort);
                datagramSocket.send(datagramPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        datagramSocket.close();
    }
}
