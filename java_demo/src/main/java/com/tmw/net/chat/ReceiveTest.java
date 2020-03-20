package com.tmw.net.chat;

import com.tmw.util.ThreadPoolUtil;

import java.util.concurrent.Executor;

/**
 * @author TMW
 * @since 2020/3/20 15:30
 */
public class ReceiveTest {
    public static void main(String[] args) {
        Executor executorService = ThreadPoolUtil.getThreadPool();
        executorService.execute(new UdpThreadSend(6789, "127.0.0.1", 8888));
        executorService.execute(new UdpThreadReceive(9999));
    }
}
