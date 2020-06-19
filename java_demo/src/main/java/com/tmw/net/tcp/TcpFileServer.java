package com.tmw.net.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author TMW
 * @since 2020/3/20 11:08
 */
public class TcpFileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);

        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./java_demo/test"));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
        }
        System.out.println("接收信息。。。");
        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("接收完成了。。。。。。".getBytes());
        accept.close();
        fileOutputStream.close();
        inputStream.close();
        serverSocket.close();
    }
}
