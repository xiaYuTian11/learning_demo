// package com.tmw.socket;
//
// import com.tmw.util.RSAUtil;
// import org.junit.jupiter.api.Test;
//
// import java.io.*;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.nio.charset.StandardCharsets;
//
// /**
//  * @author TMW
//  * @date 2020/6/23 11:31
//  */
// public class TcpSocketServerDemo {
//
//     public static void main(String[] args) {
//         ServerSocket serverSocket = null;
//         Socket socket = null;
//         try {
//             serverSocket = new ServerSocket(8890);
//             socket = serverSocket.accept();
//
//             InputStream inputStream = socket.getInputStream();
//             BufferedReader bis = new BufferedReader(new InputStreamReader(inputStream));
//             String line;
//             StringBuilder sb = new StringBuilder();
//             while ((line = bis.readLine()) != null) {
//                 sb.append(line);
//             }
//             System.out.println(RSAUtil.decryptStr(sb.toString()));
//             socket.shutdownInput();
//
//             // Thread.sleep(1000 * 10);
//             OutputStream outputStream = socket.getOutputStream();
//             String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                     "<APPROOT>" +
//                     "<PUBLIC>" +
//                     "<TRX_CODE>FRZ01</TRX_CODE>" +
//                     "</PUBLIC>" +
//                     "<PRIVATE>" +
//                     "<RETCODE>0</RETCODE>" +
//                     "<RETMSG>交易成功</RETMSG>" +
//                     "</PRIVATE>" +
//                     "</APPROOT>";
//
//             // outputStream.write(RSAUtil.encryptStr(xml).getBytes(StandardCharsets.UTF_8));
//             outputStream.write(RSAUtil.encrypt(xml));
//
//             outputStream.close();
//             bis.close();
//             inputStream.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         } finally {
//             try {
//                 // 5. close socket and server socket
//                 if (socket != null) {
//                     socket.close();
//                 }
//                 if (serverSocket != null) {
//                     serverSocket.close();
//                 }
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     @Test
//     public void test01() {
//         String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                 "<APPROOT>" +
//                 "<PUBLIC>" +
//                 "<TRX_CODE>FRZ01</TRX_CODE>" +
//                 "</PUBLIC>" +
//                 "<PRIVATE>" +
//                 "<RETCODE>0</RETCODE>" +
//                 "<RETMSG>交易成功</RETMSG>" +
//                 "</PRIVATE>" +
//                 "</APPROOT>";
//         byte[] encrypt = RSAUtil.encrypt(xml);
//         String s = new String(encrypt);
//         System.out.println(new String(encrypt));
//
//         byte[] decrypt = RSAUtil.decrypt(encrypt);
//         System.out.println(new String(decrypt));
//
//         System.out.println(RSAUtil.decryptStr(s));
//
//     }
//
// }
