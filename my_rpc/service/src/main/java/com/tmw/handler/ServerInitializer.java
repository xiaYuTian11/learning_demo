package com.tmw.handler;

import com.tmw.coder.RpcDecoder;
import com.tmw.coder.RpcEncoder;
import com.tmw.entity.RpcRequest;
import com.tmw.entity.RpcResponse;
import com.tmw.serialization.JsonSerialization;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author TMW
 * @date 2020/7/23 16:18
 */
@Component
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535,0,4));
        pipeline.addLast(new RpcEncoder(RpcResponse.class,new JsonSerialization()));
        pipeline.addLast(new RpcDecoder(RpcRequest.class,new JsonSerialization()));
        pipeline.addLast(serverHandler);

    }
}
