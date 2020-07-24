package com.tmw.server;

import com.tmw.server.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author TMW
 * @date 2020/7/24 10:58
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        // if (args.length != 1) {
        //     System.err.println(
        //             "Usage: " + EchoServer.class.getSimpleName() +
        //                     " <port>");
        //     return;
        // }



        // int port = Integer.parseInt(args[0]);
        // new EchoServer(port).start();
        new EchoServer(9999).start();
    }

    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture sync = bootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + sync.channel().localAddress());
            sync.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
