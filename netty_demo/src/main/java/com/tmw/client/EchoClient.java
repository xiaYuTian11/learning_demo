package com.tmw.client;

import com.tmw.client.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author TMW
 * @date 2020/7/24 11:17
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new EchoClientHandler());
                        }
                    });
            System.out.println(3);
            ChannelFuture sync = bootstrap.connect().sync();
            System.out.println(4);
            sync.channel().closeFuture().sync();
            System.out.println(5);
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // if (args.length != 2) {
        //     System.err.println(
        //             "Usage: " + EchoClient.class.getSimpleName() +
        //                     " <host> <port>");
        //     return;
        // }
        // final String host = args[0];
        // final int port = Integer.parseInt(args[1]);
        //
        // new EchoClient(host, port).start();
        new EchoClient("127.0.0.1", 9999).start();
    }

}
