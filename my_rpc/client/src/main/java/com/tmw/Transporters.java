package com.tmw;

import com.tmw.entity.RpcRequest;
import com.tmw.entity.RpcResponse;
import com.tmw.netty.NettyClient;

public class Transporters {

    public static RpcResponse send(RpcRequest request) {

        NettyClient nettyClient = new NettyClient("127.0.0.1", 8080);

        nettyClient.connect(nettyClient.getInetSocketAddress());

        RpcResponse send = nettyClient.send(request);

        return send;

    }

}
