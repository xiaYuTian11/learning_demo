package com.tmw.netty.handler;

import com.tmw.DefaultFuture;
import com.tmw.entity.RpcRequest;
import com.tmw.entity.RpcResponse;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TMW
 * @date 2020/7/23 16:49
 */
public class ClientHandler extends ChannelDuplexHandler {
    private final Map<String, DefaultFuture> futureMap = new ConcurrentHashMap<>();


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if(msg instanceof RpcRequest){
            RpcRequest request = (RpcRequest) msg;
            futureMap.putIfAbsent(request.getRequestId(),new DefaultFuture());
        }
        super.write(ctx, msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof RpcResponse){
            RpcResponse response = (RpcResponse) msg;
            DefaultFuture defaultFuture = futureMap.get(response.getRequestId());
            defaultFuture.setResponse(response);
        }
        super.channelRead(ctx, msg);
    }

    public RpcResponse getRpcResponse(String requestId){

        try {
            DefaultFuture defaultFuture = futureMap.get(requestId);
            return defaultFuture.getResponse(10);
        }finally {
            futureMap.remove(requestId);
        }


    }

}
