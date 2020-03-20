package com.tmw.net.tcp;

import jdk.net.Sockets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author TMW
 * @since 2020/3/20 10:45
 */
public class TcpServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            // 默认本地端口
            serverSocket = new ServerSocket(9898, 50, InetAddress.getByName("127.0.0.1"));

            while (true) {
                accept = serverSocket.accept();
                inputStream = accept.getInputStream();

           /* byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                String s = new String(bytes, 0, len);
                System.out.println(s);
            }*/
                byte[] bytes = new byte[1024];
                int len;
                outputStream = new ByteArrayOutputStream();
                while ((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                System.out.println(outputStream.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
