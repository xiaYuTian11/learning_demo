package com.tmw.net.chat;

import com.tmw.util.ThreadPoolUtil;

import java.util.concurrent.Executor;

/**
 * @author TMW
 * @since 2020/3/20 15:28
 */
public class SenderTest {
    public static void main(String[] args) throws Exception {
        Executor executorService = ThreadPoolUtil.getThreadPool();
        executorService.execute(new UdpThreadSend(9876, "127.0.0.1", 9999));
        executorService.execute(new UdpThreadReceive(8888));
    }
}
