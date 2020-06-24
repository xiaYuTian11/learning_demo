package com.tmw.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * tcp socket
 *
 * @author TMW
 * @date 2020/6/23 11:15
 */
public class TcpSocketClientDemo {
    public static void main(String[] args) {
        String message = "// String privateKey = \"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMGomqgbOMkgDx7yFRTp6sEgAFckcOZB/AkmgEov0EcJQQiBh/28HjQv7kBXGqv1yaYj9LwuwIyq1WbZQjPZ7hc8tfannLGZ0eqS7/me12EXTXs6S4fqYgniWcZr9kHxCPO3rPzx9jaOSSNlwMFF/YNJx/DZGa+rQ2KLOSmFSuhRAgMBAAECgYAoumPkTO1RZzum33TNlDB581f++7v+wQvXhNBgSdP8zJZvyoYmN+UGRrpNr8P9PFDvbwpudUqritYcrj59Fy06OYyYLxMjJypRrpw/Xz6/a0UKD+mke4rkv8Dm5p29JmDq4SkxbHMmVpJ0ACthYtwR/HZ8A8gNJJ1KVN777mU+EQJBAPqK9MnhVpHYtcjP301zGKmD6MMj0EOGW/EY2/r/6NLlsEaucmUycE/jySAdvFFojAKekllQsTm0DGzDILr4Hf0CQQDF4HUyH9WmAeuJXEzY+hEURc4IKi4qoYBjTU+QnlBGRiiQXSAvejoS60H3V4ti/lY8I4Jz5ZSBhnDOZykZ8fnlAkEArWpU+Q9Bzn2DhdVsKL2LynmFONynILX1GbItQVS0oJVfJ+1DPQxmdMWjxtkOKgMFNiLwBWY25UvcdcOx+fgXOQJAMXG81yGNSUYBjPk7Wpxh2Cb5LKEdmBly811GHGTyuhyd1jTu7e4kxMV0XlHJypqzAaP93OfpC9SEK48rfggw4QJAI5ftnoOulZN3VvaIHb/s0FQtQn+iS5MTD2Rh8bmfaVXUMvSCe751ua/Au4npPykIwUrgW3N9UxnWqJ1WaT+Eug==\";\n" +
                "        // String privateKey = \\\"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMGomqgbOMkgDx7yFRTp6sEgAFckcOZB/AkmgEov0EcJQQiBh/28HjQv7kBXGqv1yaYj9LwuwIyq1WbZQjPZ7hc8tfannLGZ0eqS7/me12EXTXs6S4fqYgniWcZr9kHxCPO3rPzx9jaOSSNlwMFF/YNJx/DZGa+rQ2KLOSmFSuhRAgMBAAECgYAoumPkTO1RZzum33TNlDB581f++7v+wQvXhNBgSdP8zJZvyoYmN+UGRrpNr8P9PFDvbwpudUqritYcrj59Fy06OYyYLxMjJypRrpw/Xz6/a0UKD+mke4rkv8Dm5p29JmDq4SkxbHMmVpJ0ACthYtwR/HZ8A8gNJJ1KVN777mU+EQJBAPqK9MnhVpHYtcjP301zGKmD6MMj0EOGW/EY2/r/6NLlsEaucmUycE/jySAdvFFojAKekllQsTm0DGzDILr4Hf0CQQDF4HUyH9WmAeuJXEzY+hEURc4IKi4qoYBjTU+QnlBGRiiQXSAvejoS60H3V4ti/lY8I4Jz5ZSBhnDOZykZ8fnlAkEArWpU+Q9Bzn2DhdVsKL2LynmFONynILX1GbItQVS0oJVfJ+1DPQxmdMWjxtkOKgMFNiLwBWY25UvcdcOx+fgXOQJAMXG81yGNSUYBjPk7Wpxh2Cb5LKEdmBly811GHGTyuhyd1jTu7e4kxMV0XlHJypqzAaP93OfpC9SEK48rfggw4QJAI5ftnoOulZN3VvaIHb/s0FQtQn+iS5MTD2Rh8bmfaVXUMvSCe751ua/Au4npPykIwUrgW3N9UxnWqJ1WaT+Eug==\\\";\\n\" +\n" +
                "                \"        // String privateKey = \\\"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMGomqgbOMkgDx7yFRTp6sEgAFckcOZB/AkmgEov0EcJQQiBh/28HjQv7kBXGqv1yaYj9LwuwIyq1WbZQjPZ7hc8tfannLGZ0eqS7/me12EXTXs6S4fqYgniWcZr9kHxCPO3rPzx9jaOSSNlwMFF/YNJx/DZGa+rQ2KLOSmFSuhRAgMBAAECgYAoumPkTO1RZzum33TNlDB581f++7v+wQvXhNBgSdP8zJZvyoYmN+UGRrpNr8P9PFDvbwpudUqritYcrj59Fy06OYyYLxMjJypRrpw/Xz6/a0UKD+mke4rkv8Dm5p29JmDq4SkxbHMmVpJ0ACthYtwR/HZ8A8gNJJ1KVN777mU+EQJBAPqK9MnhVpHYtcjP301zGKmD6MMj0EOGW/EY2/r/6NLlsEaucmUycE/jySAdvFFojAKekllQsTm0DGzDILr4Hf0CQQDF4HUyH9WmAeuJXEzY+hEURc4IKi4qoYBjTU+QnlBGRiiQXSAvejoS60H3V4ti/lY8I4Jz5ZSBhnDOZykZ8fnlAkEArWpU+Q9Bzn2DhdVsKL2LynmFONynILX1GbItQVS0oJVfJ+1DPQxmdMWjxtkOKgMFNiLwBWY25UvcdcOx+fgXOQJAMXG81yGNSUYBjPk7Wpxh2Cb5LKEdmBly811GHGTyuhyd1jTu7e4kxMV0XlHJypqzAaP93OfpC9SEK48rfggw4QJAI5ftnoOulZN3VvaIHb/s0FQtQn+iS5MTD2Rh8bmfaVXUMvSCe751ua/Au4npPykIwUrgW3N9UxnWqJ1WaT+Eug==\\\";\\n\" +\n" +
                "                \"        // String privateKey = \\\"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMGomqgbOMkgDx7yFRTp6sEgAFckcOZB/AkmgEov0EcJQQiBh/28HjQv7kBXGqv1yaYj9LwuwIyq1WbZQjPZ7hc8tfannLGZ0eqS7/me12EXTXs6S4fqYgniWcZr9kHxCPO3rPzx9jaOSSNlwMFF/YNJx/DZGa+rQ2KLOSmFSuhRAgMBAAECgYAoumPkTO1RZzum33TNlDB581f++7v+wQvXhNBgSdP8zJZvyoYmN+UGRrpNr8P9PFDvbwpudUqritYcrj59Fy06OYyYLxMjJypRrpw/Xz6/a0UKD+mke4rkv8Dm5p29JmDq4SkxbHMmVpJ0ACthYtwR/HZ8A8gNJJ1KVN777mU+EQJBAPqK9MnhVpHYtcjP301zGKmD6MMj0EOGW/EY2/r/6NLlsEaucmUycE/jySAdvFFojAKekllQsTm0DGzDILr4Hf0CQQDF4HUyH9WmAeuJXEzY+hEURc4IKi4qoYBjTU+QnlBGRiiQXSAvejoS60H3V4ti/lY8I4Jz5ZSBhnDOZykZ8fnlAkEArWpU+Q9Bzn2DhdVsKL2LynmFONynILX1GbItQVS0oJVfJ+1DPQxmdMWjxtkOKgMFNiLwBWY25UvcdcOx+fgXOQJAMXG81yGNSUYBjPk7Wpxh2Cb5LKEdmBly811GHGTyuhyd1jTu7e4kxMV0XlHJypqzAaP93OfpC9SEK48rfggw4QJAI5ftnoOulZN3VvaIHb/s0FQtQn+iS5MTD2Rh8bmfaVXUMvSCe751ua/Au4npPykIwUrgW3N9UxnWqJ1WaT+Eug==\\\";\\n\" +\n" +
                "                \"        ";
        String s = sendMessage(message);
        System.out.println(s);
    }

    /**
     * 发送消息
     *
     * @param xml
     * @return
     */
    public static String sendMessage(String xml) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8890);
            socket.setSoTimeout(1000 * 60);
            System.out.println("Connected to server...");
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(xml.getBytes(StandardCharsets.UTF_8));
            System.out.println("send message to server...");
            socket.shutdownOutput();
            System.out.println("close socket out put");
            InputStream inputStream = socket.getInputStream();
            BufferedReader bis = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bis.readLine()) != null) {
                sb.append(line);
            }

            System.out.println("receive message from server...");
            System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
