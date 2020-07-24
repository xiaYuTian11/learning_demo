package com.tmw.handler;

import com.tmw.entity.RpcRequest;
import com.tmw.entity.RpcResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author TMW
 * @date 2020/7/23 16:19
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<RpcRequest> implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(msg.getRequestId());
        try{
            Object handler = handler(msg);
            rpcResponse.setResult(handler);
        }catch (Throwable throwable){
            rpcResponse.setThrowable(throwable);
            throwable.printStackTrace();
        }
        ctx.writeAndFlush(rpcResponse);
    }

    private Object handler(RpcRequest request) throws Throwable {

        Class<?> clz = Class.forName(request.getClassName());

        Object serviceBean = applicationContext.getBean(clz);

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();

        Class<?>[] parameterTypes = request.getParameterTypes();

        Object[] parameters = request.getParameters();

        FastClass fastClass = FastClass.create(serviceClass);
        FastMethod fastMethod = fastClass.getMethod(methodName,parameterTypes);

        return fastMethod.invoke(serviceBean,parameters);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
