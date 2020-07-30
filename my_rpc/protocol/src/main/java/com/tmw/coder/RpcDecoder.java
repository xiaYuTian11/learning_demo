package com.tmw.coder;

import com.tmw.api.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author TMW
 * @date 2020/7/23 15:53
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> clz;
    private Serialization serialization;

    public RpcDecoder(Class<?> clz, Serialization serialization) {
        this.clz = clz;
        this.serialization = serialization;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }

        byteBuf.markReaderIndex();
        int readInt = byteBuf.readInt();
        if (byteBuf.readableBytes() < readInt) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[readInt];
        byteBuf.readBytes(data);
        Object deSerialize = serialization.deSerialize(data, clz);
        list.add(deSerialize);
    }
}
