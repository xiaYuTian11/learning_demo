package com.tmw.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author TMW
 * @since 2020/3/20 10:45
 */
public class TcpClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9898);
            outputStream = socket.getOutputStream();
            outputStream.write("你好，tcp Server".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
