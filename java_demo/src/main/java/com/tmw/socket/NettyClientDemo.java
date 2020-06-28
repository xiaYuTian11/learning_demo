package com.tmw.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * @author TMW
 * @date 2020/6/23 11:54
 */
public class NettyClientDemo {

    public void connect(String host, int port) throws Exception {
        EventLoopGroup elg = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(elg);
            bs.channel(NioSctpChannel.class);
            bs.option(ChannelOption.SO_KEEPALIVE, true);
            bs.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new SimpleClientHandler());
                }
            });
            ChannelFuture future = bs.connect(host, port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            elg.shutdownGracefully();
        }
    }

    static class SimpleClientHandler extends ChannelInboundHandlerAdapter {
        /**
         * 接受服务端发过来的信息
         *
         * @param ctx
         * @param msg
         * @throws Exception
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("SimpleClientHandler   channelRead   ...");
            ByteBuf byteBuf = (ByteBuf) msg;
            byte[] result = new byte[byteBuf.readableBytes()];
            System.out.println("Server send: " + new String(result, StandardCharsets.UTF_8));
            byteBuf.release();
        }

        /**
         * 向服务端发送消息
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            String msg = "hello Server!";
            ByteBuf buffer = ctx.alloc().buffer(4 * msg.length());
            buffer.writeBytes(buffer);
            ctx.write(buffer);
            ctx.flush();
        }

        /**
         * 本方法用于处理异常
         *
         * @param ctx
         * @param cause
         * @throws Exception
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // 当出现异常就关闭连接
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyClientDemo client = new NettyClientDemo();
        client.connect("127.0.0.1", 8890);

    }

}
