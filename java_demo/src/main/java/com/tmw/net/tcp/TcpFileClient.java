package com.tmw.net.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author TMW
 * @since 2020/3/20 11:08
 */
public class TcpFileClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getByName("localhost"), 9999);
        OutputStream outputStream = socket.getOutputStream();
        File file = new File("./java_demo/wallhaven-687573.jpg");
        System.out.println(file.getAbsolutePath());
        // FileInputStream fileInputStream = new FileInputStream(TcpFileClient.class.getResource("/wallhaven-687573.jpg").getFile());
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }
        System.out.println("send ...");

        // 通知服务器传输完毕
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] bytes2 = new byte[1024];
        int len2;
        while ((len2 = inputStream.read(bytes2)) != -1) {
            stream.write(bytes2, 0, len2);
        }
        System.out.println(stream.toString());
        fileInputStream.close();
        outputStream.close();
        socket.close();
    }
}
