package com.tmw.coder;

import com.tmw.api.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Objects;

/**
 * @author TMW
 * @date 2020/7/23 16:04
 */
public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> clz;
    private Serialization serialization;

    public RpcEncoder(Class<?> clz, Serialization serialization) {
        this.clz = clz;
        this.serialization = serialization;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (Objects.nonNull(clz)) {
            byte[] serialize = serialization.serialize(msg);
            out.writeInt(serialize.length);
            out.writeBytes(serialize);
        }
    }
}
