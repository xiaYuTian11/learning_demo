package com.tmw;

import com.tmw.entity.RpcResponse;

/**
 * @author TMW
 * @date 2020/7/23 16:38
 */
public class DefaultFuture {
    private RpcResponse rpcResponse;
    private volatile boolean isSucceed = false;
    private final Object object = new Object();

    public RpcResponse getResponse(int timeout) {
        synchronized (object) {
            while (!isSucceed) {
                try {
                    object.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return rpcResponse;
        }
    }

    public void setResponse(RpcResponse rpcResponse) {
        if (isSucceed) {
            return;
        }

        synchronized (object) {
            this.rpcResponse = rpcResponse;
            this.isSucceed = true;
            object.notify();
        }
    }

}
