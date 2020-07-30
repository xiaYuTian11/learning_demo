package com.tmw.client;

import com.tmw.entity.RpcRequest;
import com.tmw.entity.RpcResponse;

import java.net.InetSocketAddress;

/**
 * @author TMW
 * @date 2020/7/23 16:53
 */
public interface Client {

    RpcResponse send(RpcRequest request);

    void connect(InetSocketAddress inetSocketAddress);

    InetSocketAddress getInetSocketAddress();

    void close();

}
